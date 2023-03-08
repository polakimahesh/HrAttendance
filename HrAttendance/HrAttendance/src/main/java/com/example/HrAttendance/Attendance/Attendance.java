package com.example.HrAttendance.Attendance;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private int userId;
    @CreationTimestamp
    private LocalDateTime date;
    private String attendanceStatus;
    private LocalDateTime inTime;
    private LocalDateTime outTime;
}
