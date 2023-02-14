package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {

    List<ProjectDTO> listAllProject();
    ProjectDTO findByProjectCode(String projectCode);
    void save(ProjectDTO project);
    void update(ProjectDTO project);
    void delete(String projectCode);
}
