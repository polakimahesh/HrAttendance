package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdatedAttendanceListDto {
    private int attendanceUserId;
    private int userId;
    private LocalDate date;
    @CreationTimestamp
    private LocalDateTime requestCreatedOn;
}
