package com.cydeo.service.impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl extends AbstractMapService<ProjectDTO,String> implements ProjectService {

    private final TaskService taskService;

    public ProjectServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO findById(String projectCode) {
        return super.findById(projectCode);
    }

    @Override
    public List<ProjectDTO> findAll() {
        return super.findAll();
    }

    @Override
    public ProjectDTO save(ProjectDTO project) {
        if (project.getProjectStatus()==null){
            project.setProjectStatus(Status.OPEN);
        }   //set to Open
        return super.save(project.getProjectCode(),project);
    }

    @Override
    public void update(ProjectDTO project) {
        if (project.getProjectStatus()==null){
            project.setProjectStatus(findById(project.getProjectCode()).getProjectStatus());
        }   //set to the same status of that project
        super.update(project.getProjectCode(), project);
    }

    @Override
    public void deleteById(String projectCode) {
        super.deleteById(projectCode);
    }

    @Override
    public void complete(ProjectDTO project) {
        project.setProjectStatus(Status.COMPLETE);
    }

    @Override
    public List<ProjectDTO> getCountedListOfProjects(UserDTO manager) {
        //Project Status page: see only tasks(completed/unfinished) by certain Manager
        //note that one project could have more than one task

        List<ProjectDTO> projectList = findAll().stream()
                .filter(project -> project.getAssignedManager().equals(manager))
                .map(project -> {

                    List<TaskDTO> taskList = taskService.findTasksByManager(manager);

                    int completedTaskCounts = (int) taskList.stream()
                            .filter(task -> task.getProject().equals(project)
                                    && task.getTaskStatus() == Status.COMPLETE).count();
                    int unfinishedTasksCounts = (int) taskList.stream()
                            .filter(task->task.getProject().equals(project)
                                    && task.getTaskStatus() == Status.COMPLETE).count();

                    project.setCompleteTaskCounts(completedTaskCounts);
                    project.setUnfinishedTaskCounts(unfinishedTasksCounts);
                    return project;

                }).collect(Collectors.toList());

    return projectList;
    }
}
