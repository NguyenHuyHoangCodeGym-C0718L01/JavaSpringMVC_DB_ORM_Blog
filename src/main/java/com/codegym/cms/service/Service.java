package com.codegym.cms.service;

import java.util.List;

public interface Service<T> {
    List<T> findAll();

    T findById(int id);

    void save(T object);

    void remove(int id);
}
