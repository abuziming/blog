package com.myblog.blog.Service.impl;

import com.myblog.blog.Service.BlogService;
import com.myblog.blog.Service.TypeService;
import com.myblog.blog.entity.Blog;
import com.myblog.blog.mapper.Blogmapper;
import com.myblog.blog.quaryentity.*;
import com.myblog.blog.util.MarkdownUtils;
import lombok.SneakyThrows;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceimpl implements BlogService {
    @Autowired
   private Blogmapper blogmapper;

    @Override
    public ShowBlog getBlogById(Integer id) {
        return blogmapper.getBlogByid(id);
    }

    /**
     * 新增博客，初始化各种属性
     * @param blog
     * @return
     */
    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        blog.setUps(0);
        return blogmapper.saveBlog(blog);
    }

    /**
     * 更新博客，修改更新时间
     * @param showBlog
     * @return
     */
    @Transactional
    @SneakyThrows
    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogmapper.updateBlog(showBlog);
    }
    @Transactional
    @Override
    public void deleteBlog(Integer id) {
         blogmapper.deletBlog(id);
    }
    //查询文章列表
    @Override
    public List<BlogQuery> getAllBlog() {
       return blogmapper.getAllBlogQuery();
    }

    /**
     * 查询博客时用的方法
     * @param searchBlog
     * @return
     */
    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogmapper.searchByTitleAndType(searchBlog);
    }

    /**
     * 首页返回博客
     * @return
     */
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogmapper.getFirstPageBlog();
    }

    /**
     * 首页推荐博客
     * @return
     */
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        return blogmapper.getAllRecommendBlog();
    }

    /**
     * 首页返回最新留言信息 ps:暂时用不到
     * @return
     */
    @Override
    public List<NewComment> getNewComment() {
        return blogmapper.getNewComment();
    }

    /**
     * 博客详情
     * @param id
     * @return
     */
    @Override
    public DetailedBlog getDetailedBlog(Integer id) {
        DetailedBlog detailedBlog = blogmapper.getDetailedBlog(id);
        if(detailedBlog==null)
        {
            throw new RuntimeException("博客不存在");
        }
        String content = detailedBlog.getContent();
        content= MarkdownUtils.markdownToHtml(content);
        detailedBlog.setContent(content);
        blogmapper.updateViews(id);
        blogmapper.getCommentCountById(id);
        return detailedBlog;
    }
    @Override
    public List<FirstPageBlog> getByTypeId(Integer typeId) {
        return blogmapper.getByTypeId(typeId);
    }
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogmapper.getSearchBlog(query);
    }
}

