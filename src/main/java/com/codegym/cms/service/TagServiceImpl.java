package com.codegym.cms.service;

import com.codegym.cms.model.Tag;
import com.codegym.cms.repository.Tag.TagRepository;
import com.codegym.cms.repository.Tag.TagRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class TagServiceImpl implements Service<Tag> {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findById(int id) {
        return tagRepository.findById(id);
    }

    @Override
    public void save(Tag object) {
        tagRepository.save(object);
    }

    @Override
    public void remove(int id) {
        tagRepository.remove(id);
    }
}
