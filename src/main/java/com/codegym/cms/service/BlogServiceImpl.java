package com.codegym.cms.service;

import com.codegym.cms.model.Blog;
import com.codegym.cms.repository.Blog.BlogRepository;
import com.codegym.cms.repository.Blog.BlogRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class BlogServiceImpl implements Service<Blog> {
    @Autowired
    private BlogRepository blogRepository = new BlogRepositoryImpl();

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Blog findById(int id) {
        return blogRepository.findById(id);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void remove(int id) {
        blogRepository.remove(id);
    }
}
