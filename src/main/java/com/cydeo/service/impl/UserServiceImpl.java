package com.cydeo.service.impl;

import com.cydeo.annotation.DefaultExceptionMessage;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.exception.TicketingProjectException;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repo.UserRepository;
import com.cydeo.service.KeycloakService;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final KeycloakService keycloakService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, MapperUtil mapperUtil, @Lazy ProjectService projectService, @Lazy TaskService taskService, KeycloakService keycloakService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.projectService = projectService;
        this.taskService = taskService;
        this.keycloakService = keycloakService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAllByIsDeletedOrderByFirstNameDesc(false).stream()
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String username) {
        User user = userRepository.findByUserNameAndIsDeleted(username, false);
        if (user == null) throw new NoSuchElementException("User not found");
        return mapperUtil.convert(user, new UserDTO());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        userDTO.setEnabled(true);
        userDTO.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        User savedUser = userRepository.save(mapperUtil.convert(userDTO, new User()));
        keycloakService.userCreate(userDTO);
        return mapperUtil.convert(savedUser, new UserDTO());
    }

    @Override
    public UserDTO update(UserDTO user) {

        //Find current user
        User user1 = userRepository.findByUserNameAndIsDeleted(user.getUserName(), false);  //has id
        //encode password:
        user.setPassWord(passwordEncoder.encode(user.getPassWord()));
        //Map update user dto to entity object
        User convertedUser = mapperUtil.convert(user, new User());   // has id?
        //set id to the converted object
        convertedUser.setId(user1.getId());
        //save the updated user in the db
        userRepository.save(convertedUser);

        return findByUserName(user.getUserName());

    }


    //just change the BaseEntity's field isDeleted to true, and save the user
    @Override
    @DefaultExceptionMessage(defaultMessage = "Failed to delete user")  //custom annotation - see annotation package
    public void delete(String username) throws TicketingProjectException {
        User user = userRepository.findByUserNameAndIsDeleted(username, false);
        if (checkIfUserCanBeDeleted(user)){
            user.setIsDeleted(true);
            user.setUserName(user.getUserName() + "-" + user.getId());          //harold@manager-2
            userRepository.save(user);
        }else{
            throw new TicketingProjectException("User can not be deleted");
        }
    }

    private boolean checkIfUserCanBeDeleted(User user){
        switch (user.getRole().getDescription()){
            case "Manager":
                List<ProjectDTO> projectDTOList = projectService.listAllNonCompletedByAssignedManager(mapperUtil.convert(user, new UserDTO()));
                return projectDTOList.size() == 0;
            case "Employee":
                List<TaskDTO> taskDTOList = taskService.listAllNonCompletedByAssignedEmployee(mapperUtil.convert(user, new UserDTO()));
                return taskDTOList.size() == 0;
            default:
                return true;
        }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        return userRepository.findByRoleDescriptionIgnoreCaseAndIsDeleted(role, false).stream()
                .map(user -> mapperUtil.convert(user, new UserDTO()))
                .collect(Collectors.toList());
    }

}
