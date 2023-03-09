package com.example.HrAttendance.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeavesListDto {
    private  int leaveId;
    private int userId;
    private LocalDate date;
    private String reason;
    private LocalDateTime requestCreatedOn;
}
