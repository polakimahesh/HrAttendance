package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CheckInRequestDto {
    private int attendanceId;
    private int userId;
    private LocalDateTime date;
    private String attendanceStatus;
    private LocalDateTime inTime;


}
