package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ProjectDTO {
    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    private LocalDate startDate;
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;

}
