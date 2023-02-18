package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    TaskDTO findById(Long taskId);
    void save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void delete(Long taskId);

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);

    void deleteByProject(ProjectDTO projectDTO);
    void completeByProject(ProjectDTO projectDTO);

    List<TaskDTO> listAllTasksByStatusIsNot(Status status);
    List<TaskDTO> listAllTasksByStatus(Status status);
}
