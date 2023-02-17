package com.cydeo.service;

import com.cydeo.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> listAllTasks();
    TaskDTO findById(Long taskId);
    void save(TaskDTO taskDTO);
    void update(TaskDTO taskDTO);
    void delete(Long taskId);

}
