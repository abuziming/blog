package com.myblog.blog.Service;

import com.myblog.blog.entity.Blog;
import com.myblog.blog.quaryentity.*;

import java.util.List;

public interface BlogService {
    ShowBlog getBlogById(Integer id);
    //增加博客
    int saveBlog(Blog blog);
    //修改博客
    int updateBlog(ShowBlog showBlog);
    //删除博客
    void deleteBlog(Integer id);
    List<BlogQuery> getAllBlog();

    List<BlogQuery> getBlogBySearch(SearchBlog searchBlog);
    List<FirstPageBlog> getAllFirstPageBlog();
    List<RecommendBlog> getRecommendedBlog();
    List<NewComment> getNewComment();
    DetailedBlog getDetailedBlog(Integer id);
    List<FirstPageBlog> getByTypeId(Integer typeId);
    //首页查询博客
    List<FirstPageBlog> getSearchBlog(String query);
    List<Blog> getByTagId(Integer id);
    List<BlogQuery> getBlogByUser(Integer id);
    List<BlogQuery> getUserSearchBlog(SearchUserBlog searchUserBlog);
}
