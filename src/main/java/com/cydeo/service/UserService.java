package com.cydeo.service;

import com.cydeo.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO findById(String id);
    List<UserDTO> findAll();
    UserDTO save(UserDTO user);
    UserDTO deleteById(String username);
}
