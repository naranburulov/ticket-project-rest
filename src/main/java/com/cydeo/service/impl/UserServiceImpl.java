package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repo.RoleRepository;
import com.cydeo.repo.UserRepository;
import com.cydeo.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;

    public UserServiceImpl(RoleRepository roleRepository,
                           UserRepository userRepository, MapperUtil mapperUtil) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll(Sort.by("firstName")).stream()
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        return null;
    }

    @Override
    public void save(UserDTO user) {

    }

    @Override
    public void deleteByUserName(String username) {

    }
}
