package com.cydeo.dto;

import com.cydeo.enums.Gender;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String passWord;
    private boolean enabled;                    //for security
    private String phone;
    private RoleDTO role;
    private Gender gender;


}
