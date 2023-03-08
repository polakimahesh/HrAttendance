package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
public class CheckInRequestDto {
    @NotNull(message = "attendance id is mandatory")
    private int attendanceId;
    @NotNull(message = "user id is mandatory")
    private int userId;
    private LocalDateTime date;
    private String attendanceStatus;
    private LocalDateTime inTime;


}
