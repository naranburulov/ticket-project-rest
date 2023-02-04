package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends AbstractMapService <UserDTO, String> implements UserService {
    @Override
    public UserDTO findById(String userName) {
        return super.findById(userName);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public UserDTO save(UserDTO user) {
        return super.save(user.getUserName(), user);
    }

    @Override
    public void update(UserDTO user) {
        super.update(user.getUserName(), user);
    }

    @Override
    public void deleteById(String userName) {
        super.deleteById(userName);
    }


    @Override
    public List<UserDTO> findManagers() {
        return super.findAll().stream()
                .filter(obj->obj.getRole().getDescription().equals("Manager"))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findEmployees() {
        return super.findAll().stream()
                .filter(obj->obj.getRole().getDescription().equals("Employee"))
                .collect(Collectors.toList());
    }
}
