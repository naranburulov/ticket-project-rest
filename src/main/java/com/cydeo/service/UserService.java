package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import com.cydeo.exception.TicketingProjectException;

import java.util.List;


public interface UserService {
    List<UserDTO> listAllUsers();
    UserDTO findByUserName(String username);
    void save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    void delete(String username) throws TicketingProjectException;

    List<UserDTO>listAllByRole(String role);
}
