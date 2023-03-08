package com.example.HrAttendance.Dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
public class AttendanceDaysDto {
    private  int id;
    private int userId;
    @CreationTimestamp
    private LocalDateTime date;
    private String attendanceStatus;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
}
