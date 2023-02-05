package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl extends AbstractMapService<TaskDTO, Long> implements TaskService {

    @Override
    public TaskDTO findById(Long id) {
        return super.findById(id);
    }

    @Override
    public List<TaskDTO> findAll() {
        return super.findAll();
    }

    @Override
    public TaskDTO save(TaskDTO task) {
        if (task.getTaskStatus()==null){
            task.setTaskStatus(Status.OPEN);
        }
        if (task.getAssignedDate()==null){
            task.setAssignedDate(LocalDate.now());
        }
        if (task.getId()==null){
            task.setId(UUID.randomUUID().getMostSignificantBits());
        }
        return super.save(task.getId(),task);
    }

    @Override
    public void update(TaskDTO task) {

        TaskDTO foundTask = findById(task.getId());
        //this method is used in TaskController,
        // and it grabs the certain task from the UI form (see Task List on Task Create page)
        // therefore, id, status, and assigned date already exist for that exact task

        task.setTaskStatus(foundTask.getTaskStatus());
        task.setAssignedDate(foundTask.getAssignedDate());

        super.update(task.getId(), task);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public List<TaskDTO> findTasksByManager(UserDTO manager) {
        return super.findAll().stream()
                .filter(taskDTO -> taskDTO.getProject().getAssignedManager().equals(manager))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByStatusIsNot(Status completeStatus) {
        return findAll().stream()
                .filter(task -> !task.getTaskStatus().equals(completeStatus))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus(Status completeStatus) {
        return findAll().stream()
                .filter(task -> task.getTaskStatus().equals(completeStatus))
                .collect(Collectors.toList());
    }

    @Override
    public void updateTaskStatus(TaskDTO task) {
        findById(task.getId()).setTaskStatus(task.getTaskStatus());
        //first, status is updated in the UI form (pending tasks - update task status)
        update(task);   //then, task is updated with new status information by using update()
    }
}
