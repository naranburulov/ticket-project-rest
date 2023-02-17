package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repo.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperUtil mapperUtil;

    public TaskServiceImpl(TaskRepository taskRepository, MapperUtil mapperUtil) {
        this.taskRepository = taskRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll(Sort.by("taskStatus")).stream()
                .map(task -> mapperUtil.convert(task, new TaskDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long taskId) {
        return mapperUtil.convert(taskRepository.findById(taskId),new TaskDTO());
    }

    @Override
    public void save(TaskDTO taskDTO) {
        taskDTO.setTaskStatus(taskDTO.getTaskStatus());
        Task task = mapperUtil.convert(taskDTO, new Task());
        taskRepository.save(task);
    }

    @Override
    public void update(TaskDTO taskDTO) {

    }

    @Override
    public void delete(Long taskId) {

    }
}
