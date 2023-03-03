package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Dto.CheckInRequestDto;
import com.example.HrAttendance.Dto.CheckOutRequestDto;
import com.example.HrAttendance.Dto.GetAttendanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping("")
    public ResponseEntity<Object> getAllAttendanceDetailsWithId(@RequestBody GetAttendanceDto getAttendanceDto){
        var attendance=attendanceService.getAllAttendanceDetailsWithId(getAttendanceDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
    @PostMapping("/clock-in")
    public ResponseEntity<Object> checkClockInRequest(@RequestBody CheckInRequestDto checkRequestDto){
        var attendance=attendanceService.checkClockInRequest(checkRequestDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
    @PostMapping("/clock-out")
    public ResponseEntity<Object> checkClockOutRequest(@RequestBody CheckOutRequestDto checkOutRequestDto){
        var attendance=attendanceService.checkClockOutRequest(checkOutRequestDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
}
