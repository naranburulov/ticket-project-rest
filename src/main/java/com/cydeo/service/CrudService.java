package com.cydeo.service;

import java.util.List;

public interface CrudService<T, ID>{

    T findById(ID id);
    List<T> findAll();
    T save(T object);
    void update(T object);
    void deleteById(ID id);
}
