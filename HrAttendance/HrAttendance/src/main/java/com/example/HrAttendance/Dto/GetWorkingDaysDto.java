package com.example.HrAttendance.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GetWorkingDaysDto {
    private  int userId;
    private List<AttendanceDaysDto> attendancePresentDaysDtoList;
    private long countOfPresentDays;
    private  long countOfAbsentDays;
    private long countOfWeekEndDays;
    private long countOfLeaves;
    private int totalWorkingDaysInMonth;


}
