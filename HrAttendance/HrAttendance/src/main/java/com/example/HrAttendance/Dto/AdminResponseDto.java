package com.example.HrAttendance.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminResponseDto {
    private int userId;
    private String status;
    private int attendanceUserId;
    private boolean isAdmin;

}
