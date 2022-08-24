package com.myblog.blog.Service.impl;

import com.myblog.blog.Service.TagService;
import com.myblog.blog.entity.Tag;
import com.myblog.blog.mapper.Tagmapper;
import lombok.SneakyThrows;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceimpl implements TagService {
    @Autowired
    private  Tagmapper tagmapper;

    @Override
    public int saveTag(Tag tag) {
        return tagmapper.saveTag(tag);
    }

    @Override
    public void deleteTag(Integer id) {
        tagmapper.deleteTag(id);
    }

    @SneakyThrows
    @Override
    public int updateTag(Tag tag) {
        Tag tag1=tagmapper.getTagById(tag.getId());
        if(tag1 == null)
        {
            throw new NotFoundException("该标签不存在");
        }
        else
            BeanUtils.copyProperties(tag1,tag);
        return tagmapper.saveTag(tag1);
    }

    @Override
    public Tag getTagById(Integer id) {
        return tagmapper.getTagById(id);
    }

    @Override
    public List<Tag> getAllTag(){
        return tagmapper.getAllTags();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagmapper.getTagByName(name);
    }
}
