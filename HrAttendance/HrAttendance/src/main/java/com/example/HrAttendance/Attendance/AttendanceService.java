package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Dto.CheckInRequestDto;
import com.example.HrAttendance.Dto.CheckOutRequestDto;
import com.example.HrAttendance.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UsersRepository usersRepository;

    public HashMap<String,Object> checkClockInRequest(CheckInRequestDto checkRequestDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        Attendance attendance = new Attendance();
        var users=usersRepository.findById(checkRequestDto.getUserId());
        LocalDateTime localDateTime=LocalDateTime.now();
        if(users.isPresent()){
            attendance.setUserId(checkRequestDto.getUserId());
            attendance.setInTime(localDateTime);
            attendance.setDate(localDateTime);
            attendance.setAttendanceStatus("Present");
            attendanceRepository.save(attendance);
            response.put("isSuccess",true);
            response.put("message",attendance);
            return  response;
        }
        response1.put("message","Incorrect userid "+ checkRequestDto.getUserId());
        response.put("message",response1);
        return response;
    }

    public HashMap<String,Object> checkClockOutRequest(CheckOutRequestDto checkOutRequestDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        LocalDateTime localDateTime=LocalDateTime.now();
        var attendance=attendanceRepository.findById(checkOutRequestDto.getAttendanceId()).orElse(null);
        if(attendance==null){
            response1.put("message","incorrect user attendance id "+checkOutRequestDto.getAttendanceId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
            attendance.setOutTime(localDateTime);
            attendanceRepository.save(attendance);
            response.put("isSuccess",true);
            response.put("message",attendance);
            return  response;

        }

    }
}
