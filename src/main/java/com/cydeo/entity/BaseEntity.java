package com.cydeo.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BaseEntity {
    private Long id;
    private LocalDateTime insertDateTime;
    private Long insertUserId;
    private LocalDateTime lastUpdateDateTime;
    private Long lastUpdateUserId;
}
