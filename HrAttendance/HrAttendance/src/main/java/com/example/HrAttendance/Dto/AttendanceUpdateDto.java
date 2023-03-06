package com.example.HrAttendance.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class AttendanceUpdateDto {
    private int userId;
    private LocalDate date;
}
