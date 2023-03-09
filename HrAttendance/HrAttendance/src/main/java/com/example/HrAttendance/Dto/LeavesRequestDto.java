package com.example.HrAttendance.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeavesRequestDto {
    private int userId;
    private String reason;
    private LocalDate date;
}
