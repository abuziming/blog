package com.myblog.blog.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.myblog.blog.Service.BlogService;
import com.myblog.blog.Service.TagService;
import com.myblog.blog.Service.TypeService;
import com.myblog.blog.entity.Blog;
import com.myblog.blog.entity.Type;
import com.myblog.blog.entity.User;
import com.myblog.blog.mapper.Blogmapper;
import com.myblog.blog.quaryentity.BlogQuery;
import com.myblog.blog.quaryentity.SearchBlog;
import com.myblog.blog.quaryentity.ShowBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.channels.Pipe;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
    //博客列表
    @GetMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "id desc";
        PageHelper.startPage(pageNum,5,orderBy);
        List<BlogQuery> list = blogService.getAllBlog();
        PageInfo<BlogQuery> pageInfo=new PageInfo<>(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("types",typeService.getTypes());
        return "admin/blogs";
    }
    //跳转博客新增页面
    @GetMapping("/blogs/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getTypes());
        model.addAttribute("blog", new Blog());
        model.addAttribute("tags",tagService.getAllTag());
        return "admin/blogs-input";
    }
    //博客新增
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());
        //如果标签不为空时设置标签，为空时跳过
        if(StringUtil.isNotEmpty(blog.getTagIds()))
        blog.setTags(tagService.findUseTag(blog.getTagIds()));
        int b = blogService.saveBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Integer id, Model model) {
        List<Type> allType = typeService.getTypes();
        model.addAttribute("tags",tagService.getAllTag());
        model.addAttribute("blog", blogService.getBlogById(id));
        model.addAttribute("types", allType);
        return "admin/blogs-input";
    }
    //编辑修改文章
    @PostMapping("/blogs/{id}")
    public String editPost(ShowBlog showBlog, RedirectAttributes attributes) {
        if(StringUtil.isNotEmpty(showBlog.getTagIds()))
            showBlog.setTags(tagService.findUseTag(showBlog.getTagIds()));
       if(showBlog.getTags()!=null)
       {
           tagService.deleteByBlogId(showBlog.getId());
       }
        int b = blogService.updateBlog(showBlog);
        if(b == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }
    //删除
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Integer id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<BlogQuery> blogBySearch = blogService.getBlogBySearch(searchBlog);
        System.out.println("进入");
        System.out.println(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs :: blogList";
    }
}
