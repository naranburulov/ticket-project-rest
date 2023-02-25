package com.cydeo.unitTest;

import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repo.UserRepository;
import com.cydeo.service.KeycloakService;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectService projectService;
    @Mock
    private TaskService taskService;
    @Mock
    private KeycloakService keycloakService;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private MapperUtil mapperUtil = new MapperUtil(new ModelMapper());

    User user;
    UserDTO userDTO;


    @BeforeEach
    void setUp(){
        user = new User();
        user.setId(1L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setUserName("user");
        user.setPassWord("Abc1");
        user.setEnabled(true);
        user.setRole(new Role("Manager"));

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setUserName("user");
        userDTO.setPassWord("Abc1");
        userDTO.setEnabled(true);
            RoleDTO roleDTO = new RoleDTO();
        roleDTO.setDescription("Manager");

    }

    private List<User> getUsers(){
        User user2 = new User();
        user2.setId(2L);
        user2.setFirstName("Emily");
        return List.of(user, user2);
    }

    private List<UserDTO> getUserDTOs(){
        UserDTO userDTO2 = new UserDTO();
        userDTO2.setId(2L);
        userDTO2.setFirstName("Emily");
        return List.of(userDTO, userDTO2);
    }

    @Test
    void shouldListAllUsers(){
        //stub
        when(userRepository.findAllByIsDeletedOrderByFirstNameDesc(false)).thenReturn(getUsers());
        List<UserDTO> expectedList = getUserDTOs();

        List<UserDTO> actualList = userService.listAllUsers();

//        assertEquals(expectedList,actualList);
// can't compare objects' data of two list w/ assertEquals(),
// for the objects are different, even though the data of the objects is the same

        //AssertJ
        assertThat(actualList).usingRecursiveComparison()    //usingRecursiveComparison() - compares fields of the objs
                .ignoringExpectedNullFields().isEqualTo(expectedList);
    }

    @Test
    void shouldFindUserByUserName(){
        //stub
        when(userRepository.findByUserNameAndIsDeleted(anyString(), anyBoolean())).thenReturn(user);

        //return obj
        UserDTO actual = userService.findByUserName("user");

        //expected
        assertThat(actual).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(userDTO);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound(){
        //when(userRepository.findByUserNameAndIsDeleted(anyString(), anyBoolean())).thenReturn(null);

        Throwable throwable = catchThrowable(() -> userService.findByUserName("SomeUserName"));

        assertInstanceOf(NoSuchElementException.class,throwable);
        assertEquals("User not found", throwable.getMessage());
    }

}
