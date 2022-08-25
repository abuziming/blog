package com.myblog.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.myblog.blog.entity.Comment;
@Mapper
@Repository
public interface Commentmapper {
    //根据博客id查询评论信息
    List<Comment> listCommentByBlogId(Long Id);
   List<Comment> saveAllCComment();
    //添加保存评论
    int saveComment(Comment comment,Comment parentComment);

    //删除评论
    void deleteComment(Comment comment,Long id);

    // 根据父评论id查询留言信息
    Comment getEmailByParentId(Long parentCommentId);


}
