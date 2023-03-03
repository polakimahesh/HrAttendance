package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
public class AttendanceResponseDto {
    private  int attendanceId;
    private String date;

    private String attendanceStatus;

    private String inTime;

    private String outTime;
}
