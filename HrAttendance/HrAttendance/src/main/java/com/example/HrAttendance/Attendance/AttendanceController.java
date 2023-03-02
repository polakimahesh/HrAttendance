package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Dto.CheckInRequestDto;
import com.example.HrAttendance.Dto.CheckOutRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping("clock-in")
    public ResponseEntity<Object> checkClockInRequest(@RequestBody CheckInRequestDto checkRequestDto){
        var attendance=attendanceService.checkClockInRequest(checkRequestDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
    @PostMapping("clock-out")
    public ResponseEntity<Object> checkClockOutRequest(@RequestBody CheckOutRequestDto checkOutRequestDto){
        var attendance=attendanceService.checkClockOutRequest(checkOutRequestDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
}
