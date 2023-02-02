package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface CrudService<T, ID>{

    T findById(ID id);
    List<T> findAll();
    T save(UserDTO user);
    T deleteById(ID username);
}
