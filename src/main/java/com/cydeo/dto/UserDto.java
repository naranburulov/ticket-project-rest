package com.cydeo.dto;

import com.cydeo.entity.Role;
import com.cydeo.enums.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private boolean enabled;                    //for security
    private String phone;
    private Role role;
    private Gender gender;


}
