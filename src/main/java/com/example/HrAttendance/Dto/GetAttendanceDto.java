package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetAttendanceDto {
    private int userId;
    private List<AttendanceResponseDto> attendanceResponseDtos;
}
