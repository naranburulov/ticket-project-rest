package com.cydeo.controller;

import com.cydeo.dto.ResponseWrapper;
import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.repo.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;

    public TaskController(TaskService taskService,
                          TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllTask(){
        List<TaskDTO> taskDTOList = taskService.listAllTasks();
        return ResponseEntity.ok(new ResponseWrapper("Task are successfully retrieved", taskDTOList, HttpStatus.OK));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> getTaskById(@PathVariable ("taskId") Long taskId){
        TaskDTO taskDTO = taskService.findById(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully retrieved", taskDTO, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO){
        taskService.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO){
        taskService.update(taskDTO);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable ("taskId") Long taskId){
        taskService.delete(taskId);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully deleted", HttpStatus.OK));
    }

    @GetMapping("/employee/pending-tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks(){
        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Employee-pending tasks are successfully retrieved", taskDTOList, HttpStatus.OK));
    }

    @PutMapping("/employee/update")
    public ResponseEntity<ResponseWrapper> employeeUpdateTasks(@RequestBody TaskDTO taskDTO){
        taskService.update(taskDTO);
        return ResponseEntity.ok(new ResponseWrapper("Task is successfully updated", HttpStatus.OK));
    }

    @GetMapping("/employee/archive")
    public ResponseEntity<ResponseWrapper> employeeArchiveTasks(){
        List<TaskDTO> taskDTOList = taskService.listAllTasksByStatus(Status.COMPLETE);
        return ResponseEntity.ok(new ResponseWrapper("Tasks are successfully retrieved", taskDTOList, HttpStatus.OK));
    }
}
