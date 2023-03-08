package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;
    @PostMapping("")
    public ResponseEntity<Object> getAllAttendanceDetailsWithId(@RequestBody GetAttendanceDto getAttendanceDto){
        HashMap<String,Object> attendance=attendanceService.getAllAttendanceDetailsWithId(getAttendanceDto);
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

    @PostMapping("/attendance-update-request")
    public ResponseEntity<Object> requestForAttendanceUpdate(@RequestBody AttendanceUpdateDto attendanceUpdateDto){
        var attendance=attendanceService.userRequestForAttendanceUpdate(attendanceUpdateDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
    @GetMapping("/list-of-all-updated-attendance")
    public ResponseEntity<Object> getListOfAllAttendanceUsers(){
        return new ResponseEntity<>(attendanceService.getListOfAllAttendanceUsers(), HttpStatus.OK);
    }
    @PostMapping("/get-all-updated-employee-with-id")
    public ResponseEntity<Object>  getUpdatedAttendanceWithSpecificId(@RequestBody GetUpdatedAttendanceList getUpdatedAttendanceList){
        var getAttendance=attendanceService.getUpdatedAttendanceWithSpecificId(getUpdatedAttendanceList);
        if(Boolean.TRUE.equals(getAttendance.get("isSuccess"))){
            return ResponseEntity.ok(getAttendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(getAttendance.get("message"));
    }
    @PostMapping("/get-no-of-days-present-with-specific-user")
    public ResponseEntity<Object> getNoOfDaysPresentWithSpecificUser(@RequestBody GetWorkingDaysDto getWorkingDaysDto){
        var attendance =attendanceService.getNoOfDaysPresentWithSpecificUser(getWorkingDaysDto);
        if(Boolean.TRUE.equals(attendance.get("isSuccess"))){
            return ResponseEntity.ok(attendance.get("message"));
        }else
            return ResponseEntity.badRequest().body(attendance.get("message"));
    }
}
