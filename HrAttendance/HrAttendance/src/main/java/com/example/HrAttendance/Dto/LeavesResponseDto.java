package com.example.HrAttendance.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeavesResponseDto {
    private  int userId;
    private int leaveId;
    private String status;

}
