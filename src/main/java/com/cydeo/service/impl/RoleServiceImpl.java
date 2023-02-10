package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;
import com.cydeo.repo.RoleRepository;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDTO> listAllRoles() {

        return ;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }
}
