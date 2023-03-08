package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
public class AttendanceResponseDto {
    private  int attendanceId;
    private String date;

    private String attendanceStatus;

    private String inTime;
    private String outTime;

//    public AttendanceResponseDto(){
//        LocalTime defaultInTime=;
//        LocalTime defaultOutTime=;
//        this.inTime= String.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.of(9,30)));
//        this.outTime= String.valueOf(LocalDateTime.of(LocalDate.now(),LocalTime.of(18,30)));
//    }
}
