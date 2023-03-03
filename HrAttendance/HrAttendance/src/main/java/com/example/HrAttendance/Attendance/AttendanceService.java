package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Dto.AttendanceResponseDto;
import com.example.HrAttendance.Dto.CheckInRequestDto;
import com.example.HrAttendance.Dto.CheckOutRequestDto;
import com.example.HrAttendance.Dto.GetAttendanceDto;
import com.example.HrAttendance.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UsersRepository usersRepository;


    public HashMap<String,Object> getAllAttendanceDetailsWithId(GetAttendanceDto getAttendanceDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users=usersRepository.findById(getAttendanceDto.getUserId()).orElse(null);
        if(users==null){
            response1.put("message","incorrect User id "+getAttendanceDto.getUserId());
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
            List<Attendance> attendances= attendanceRepository.findByUserId(getAttendanceDto.getUserId());
            List<AttendanceResponseDto> attendanceResponses=new ArrayList<>();
            for(Attendance attendance:attendances){
                AttendanceResponseDto attendanceResponse=new AttendanceResponseDto();
                attendanceResponse.setAttendanceId(attendance.getId());
                attendanceResponse.setAttendanceStatus(attendance.getAttendanceStatus());
                if (attendance.getOutTime() == null) {
                        attendanceResponse.setOutTime("");
                }else{
                    attendanceResponse.setOutTime(attendance.getOutTime().getHour()+":"+attendance.getOutTime().getMinute()+":"+attendance.getOutTime().getSecond());
                }
                attendanceResponse.setDate(attendance.getDate().getDayOfMonth()+"/"+attendance.getDate().getMonthValue()+"/"+attendance.getDate().getDayOfWeek());
                attendanceResponse.setInTime(attendance.getInTime().getHour()+":"+attendance.getInTime().getMinute()+":"+attendance.getInTime().getSecond());
                attendanceResponses.add(attendanceResponse);
            }
            getAttendanceDto.setUserId(users.getId());
            getAttendanceDto.setAttendanceResponseDtos(attendanceResponses);
            response.put("isSuccess",true);
            response.put("message",getAttendanceDto);
            return response;
        }
    }
    @Transactional
    public HashMap<String,Object> checkClockInRequest(CheckInRequestDto checkRequestDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        Attendance attendance = new Attendance();
        var users=usersRepository.findById(checkRequestDto.getUserId());
        LocalDateTime localDateTime=LocalDateTime.now();
//        String date=localDateTime.getYear()+"-"+localDateTime.getMonth()+"-"+localDateTime.getDayOfMonth();
        var attendanceByDate=attendanceRepository.findByDate(localDateTime.toLocalDate());
        if (users.isPresent()) {
            if (attendanceByDate == null) {
                attendance.setUserId(checkRequestDto.getUserId());
                attendance.setDate(localDateTime);
                attendance.setAttendanceStatus("present");
                attendance.setInTime(localDateTime);

                attendanceRepository.save(attendance);

                checkRequestDto.setAttendanceId(attendance.getId());
                checkRequestDto.setUserId(attendance.getUserId());
                checkRequestDto.setDate(attendance.getDate());
                checkRequestDto.setInTime(attendance.getInTime());
                checkRequestDto.setAttendanceStatus(attendance.getAttendanceStatus());
//            attendanceRepository.save(attendance);

                response.put("isSuccess", true);
                response.put("message", checkRequestDto);
                return response;
            }else  {
                response1.put("message","Employee already clock in Thank You!   " +checkRequestDto.getUserId());
                response.put("message",response1);
                return response;
            }
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
