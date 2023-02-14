package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject(Model model, @ModelAttribute("username") String username){
        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.listAllByRole("manager"));
        model.addAttribute("projects", projectService.listAllProject());
        return "project/create";
    }
//
//    @PostMapping("/create")
//    public String insertProject(@ModelAttribute("project") ProjectDTO project){
//        projectService.save(project);
//        return "redirect:/project/create";
//    }

//    @GetMapping("/complete/{projectCode}")
//    public String completeProject(@PathVariable("projectCode") String projectCode){
//        projectService.complete(projectService.findById(projectCode));
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/update/{projectCode}")
//    public String editProject(@PathVariable("projectCode") String projectCode, Model model){
//        model.addAttribute("project", projectService.findById(projectCode));
//        model.addAttribute("managers", userService.findManagers());
//        model.addAttribute("projects", projectService.findAll());
//        return "/project/update";
//    }
//
//    @PostMapping("/update")
//    public String updateProject(@ModelAttribute("project") ProjectDTO project){
//        projectService.update(project);
//        return "redirect:/project/create";
//    }
//
//    @GetMapping("/delete/{projectCode}")
//    public String deleteProject(@PathVariable("projectCode") String projectCode){
//        projectService.deleteById(projectCode);
//        return "redirect:/project/create";
//    }
//
//
//    @GetMapping("/manager/project-status")
//    public String getProjectByManager(Model model){
//        UserDTO manager = userService.findById("john@cydeo.com");   //temporary hard-coded (will change after security)
//        List<ProjectDTO> projects = projectService.getCountedListOfProjects(manager);
//
//        model.addAttribute("projects", projects);
//        return "/manager/project-status";
//    }

}
