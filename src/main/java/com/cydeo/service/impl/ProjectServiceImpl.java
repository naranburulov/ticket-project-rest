package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repo.ProjectRepository;
import com.cydeo.service.ProjectService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperUtil mapperUtil;

    public ProjectServiceImpl(ProjectRepository projectRepository, MapperUtil mapperUtil) {
        this.projectRepository = projectRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<ProjectDTO> listAllProject() {
        return projectRepository.findAll(Sort.by("projectCode")).stream()
                .map(project -> mapperUtil.convert(project, new ProjectDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        return mapperUtil.convert(projectRepository.findByProjectCode(projectCode), new ProjectDTO());
    }

    @Override
    public void save(ProjectDTO projectDTO) {
        projectDTO.setProjectStatus(Status.OPEN);
        Project project = mapperUtil.convert(projectDTO, new Project());
        projectRepository.save(project);
    }

    @Override
    public void update(ProjectDTO projectDTO) {
        Project project = projectRepository.findByProjectCode(projectDTO.getProjectCode());
        Project convertedProject = mapperUtil.convert(projectDTO, new Project());
        convertedProject.setId(project.getId());
        convertedProject.setProjectStatus(project.getProjectStatus());
        projectRepository.save(convertedProject);
    }

    @Override
    public void delete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setIsDeleted(true);
        projectRepository.save(project);
    }

    @Override
    public void complete(String projectCode) {
        Project project = projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
    }
}
