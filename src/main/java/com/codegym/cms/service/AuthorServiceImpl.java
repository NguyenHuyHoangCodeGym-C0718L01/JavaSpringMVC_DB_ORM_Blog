package com.codegym.cms.service;

import com.codegym.cms.model.Author;
import com.codegym.cms.repository.Author.AuthorRepository;
import com.codegym.cms.repository.Author.AuthorRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class AuthorServiceImpl implements Service<Author> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id);
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void remove(int id) {
        authorRepository.remove(id);
    }
}
