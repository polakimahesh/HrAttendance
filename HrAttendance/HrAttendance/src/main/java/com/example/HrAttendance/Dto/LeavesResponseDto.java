package com.example.HrAttendance.Dto;

import lombok.Data;

@Data
public class LeavesResponseDto {
    private  int userId;
    private int leaveId;
    private String status;
//    private boolean isAdmin;
}
