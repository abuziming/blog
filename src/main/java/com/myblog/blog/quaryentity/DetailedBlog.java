package com.myblog.blog.quaryentity;

import com.myblog.blog.entity.Tag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 博客详情实体类
 */
public class DetailedBlog {

    //博客信息
    private Integer id;
    private String firstPicture;
    private String flag;
    private String title;
    private String content;
    private Integer views;
    private Integer commentCount;
    private Date updateTime;
    private boolean commentabled;
    private boolean shareStatement;
    private boolean appreciation;
    private long ups;
    private String nickname;
    private String avatar;

    //分类名称
    private String typeName;

    List<Tag> tags=new ArrayList<>();

    @Override
    public String toString() {
        return "DetailedBlog{" +
                "id=" + id +
                ", firstPicture='" + firstPicture + '\'' +
                ", flag='" + flag + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", views=" + views +
                ", commentCount=" + commentCount +
                ", updateTime=" + updateTime +
                ", commentabled=" + commentabled +
                ", shareStatement=" + shareStatement +
                ", appreciation=" + appreciation +
                ", ups=" + ups +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", typeName='" + typeName + '\'' +
                ", tags=" + tags +
                '}';
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public DetailedBlog() {
    }

    public long getUps() {
        return ups;
    }

    public void setUps(long ups) {
        this.ups = ups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(String firstPicture) {
        this.firstPicture = firstPicture;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isCommentabled() {
        return commentabled;
    }

    public void setCommentabled(boolean commentabled) {
        this.commentabled = commentabled;
    }

    public boolean isShareStatement() {
        return shareStatement;
    }

    public void setShareStatement(boolean shareStatement) {
        this.shareStatement = shareStatement;
    }

    public boolean isAppreciation() {
        return appreciation;
    }

    public void setAppreciation(boolean appreciation) {
        this.appreciation = appreciation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}