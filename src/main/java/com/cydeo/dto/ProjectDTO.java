package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;


}
