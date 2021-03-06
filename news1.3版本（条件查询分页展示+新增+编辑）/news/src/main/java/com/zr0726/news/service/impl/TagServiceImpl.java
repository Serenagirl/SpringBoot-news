package com.zr0726.news.service.impl;

import com.zr0726.news.dao.TagRepository;
import com.zr0726.news.po.Tag;
import com.zr0726.news.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findByName(name);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.findById(id);
    }

    @Override
    public Tag getTag(Long id) {
        return tagRepository.findById(id).orElse(null);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagRepository.findById(id).orElse(null);
        if (tag1==null){
            System.out.println("未获得更新对象");
            return null;
        }
        BeanUtils.copyProperties(tag, tag1);
        return tagRepository.save(tag1);

    }

    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRepository.findAllById(convertTolist(ids));
    }

    private List<Long> convertTolist(String ids){
        System.out.println("service接收ids为，"+ids);
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids!=null){
            String[] idArray = ids.split(",");
            for (int i=0;i<idArray.length;i++){
                list.add(new Long(idArray[i]));
            }
        }
        System.out.println("service中处理完成后的id list"+list);
        return list;
    }
}
