package com.cydeo.service;

import com.cydeo.dto.RoleDTO;
import java.util.List;

public interface RoleService {

    RoleDTO findById(Long id);
    List<RoleDTO> findAll();
    RoleDTO save(RoleDTO role);

    void deleteById(Long id);



}
