package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Dto.*;
import com.example.HrAttendance.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AttendanceUserRepository attendanceUserRepository;

    public HashMap<String,Object> getAllAttendanceDetailsWithId(GetAttendanceDto getAttendanceDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users=usersRepository.findById(getAttendanceDto.getUserId()).orElse(null);
        if(users==null){
            response1.put("message","incorrect User id "+getAttendanceDto.getUserId());
            response.put("isSuccess",false);
            response.put("message",response1);
        }else{
            List<Attendance> attendances = attendanceRepository.findByUserId(getAttendanceDto.getUserId());
            List<AttendanceResponseDto> attendanceResponses = new ArrayList<>();
            for (Attendance attendance : attendances) {
                AttendanceResponseDto attendanceResponse = new AttendanceResponseDto();
                attendanceResponse.setAttendanceId(attendance.getId());
                attendanceResponse.setAttendanceStatus(attendance.getAttendanceStatus());
                if (attendance.getOutTime() == null) {
                    attendanceResponse.setOutTime("");
                } else {
                    attendanceResponse.setOutTime(attendance.getOutTime().getHour() + ":" + attendance.getOutTime().getMinute() + ":" + attendance.getOutTime().getSecond());
                }
                attendanceResponse.setDate(attendance.getDate().getDayOfMonth() + "/" + attendance.getDate().getMonthValue() + "/" + attendance.getDate().getDayOfWeek());
                attendanceResponse.setInTime(attendance.getInTime().getHour() + ":" + attendance.getInTime().getMinute() + ":" + attendance.getInTime().getSecond());
                attendanceResponses.add(attendanceResponse);
            }
            getAttendanceDto.setUserId(users.getId());
            getAttendanceDto.setAttendanceResponseDtos(attendanceResponses);
            response.put("isSuccess", true);
            response.put("message", getAttendanceDto);
        }
        return  response;
    }
    @Transactional
    public HashMap<String,Object> checkClockInRequest(CheckInRequestDto checkRequestDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        Attendance attendance = new Attendance();
        var users=usersRepository.findById(checkRequestDto.getUserId()).orElse(null);
        LocalDateTime localDateTime=LocalDateTime.now();
//        String date=localDateTime.getYear()+"-"+localDateTime.getMonth()+"-"+localDateTime.getDayOfMonth();
        var attendanceUser=attendanceRepository.findByUserIdAndDate(checkRequestDto.getUserId(),localDateTime.toLocalDate());
        if (users!=null) {
            if (attendanceUser == null) {
                attendance.setUserId(checkRequestDto.getUserId());
                attendance.setDate(localDateTime);
                attendance.setAttendanceStatus("Present");
                attendance.setInTime(localDateTime);
                //attendance is saving into the repository

                attendanceRepository.save(attendance);

                //i want to get specific fields so i taken this object to convert and return the request body
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
        var user = usersRepository.findById((checkOutRequestDto.getUserId()));
        if (user.isPresent()) {
            var attendance = attendanceRepository.findById(checkOutRequestDto.getAttendanceId()).orElse(null);
            if (attendance == null) {
                response1.put("message", "incorrect user attendance id " + checkOutRequestDto.getAttendanceId() + ", please enter the valid id!");
                response.put("isSuccess", false);
                response.put("message", response1);
//                return response;
            } else {
                attendance.setOutTime(localDateTime);
                attendanceRepository.save(attendance);
                response.put("isSuccess", true);
                response.put("message", attendance);
            }
        } else {
            response1.put("message", "Employee already clock out Thank You!   " + checkOutRequestDto.getAttendanceId());
            response.put("isSuccess", false);
            response.put("message", response1);
        }
        return response;
    }
    @Transactional
    public HashMap<String,Object> userRequestForAttendanceUpdate(AttendanceUpdateDto attendanceUpdateDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");

        var users=usersRepository.findById(attendanceUpdateDto.getUserId()).orElse(null);
        if(users!=null){
        List<AttendanceUser> attendanceUser=attendanceUserRepository.findByUserId(attendanceUpdateDto.getUserId());
            if (attendanceUser == null || attendanceUpdateDto.getDate()!=null) {
                AttendanceUser attendanceUser1 = new AttendanceUser();
                attendanceUser1.setUserId(attendanceUpdateDto.getUserId());
                attendanceUser1.setDate(LocalDate.parse((attendanceUpdateDto.getDate().format(dateTimeFormatter))));
                attendanceUserRepository.save(attendanceUser1);
                response.put("isSuccess", true);
                response.put("message", attendanceUser1);
            } else {
                response1.put("message", "already update " + attendanceUpdateDto.getUserId() + ", id!");
                response.put("isSuccess", false);
                response.put("message", response1);
            }
            return response;
        }
        response1.put("message","Incorrect userid "+ attendanceUpdateDto.getUserId());
        response.put("message",response1);
        return response;
    }



    public  List<AttendanceUser> getListOfAllAttendanceUsers(){
        return attendanceUserRepository.findAll();
    }
    @Transactional
    public HashMap<String,Object> getUpdatedAttendanceWithSpecificId(GetUpdatedAttendanceList getUpdatedAttendanceList){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var user=usersRepository.findById(getUpdatedAttendanceList.getUserId());
        if(user.isEmpty()){
            response1.put("message","incorrect User id "+ getUpdatedAttendanceList.getUserId());
            response.put("isSuccess",false);
            response.put("message",response1);
        }else {
            List<AttendanceUser> attendanceUsers=attendanceUserRepository.findByUserId(getUpdatedAttendanceList.getUserId());
            List<UpdatedAttendanceListDto> updatedAttendanceListDtos =new ArrayList<>();
            for(AttendanceUser attendanceUser:attendanceUsers){
                UpdatedAttendanceListDto updatedAttendanceListDto= new UpdatedAttendanceListDto();
                updatedAttendanceListDto.setAttendanceUserId(attendanceUser.getId());
                updatedAttendanceListDto.setUserId(attendanceUser.getUserId());
                updatedAttendanceListDto.setDate(attendanceUser.getDate());
                updatedAttendanceListDto.setRequestCreatedOn(attendanceUser.getRequestCreatedOn());
                updatedAttendanceListDtos.add(updatedAttendanceListDto);
            }
        getUpdatedAttendanceList.setUpdatedAttendanceListDtos(updatedAttendanceListDtos);
//        getUpdatedAttendanceList.setUserId(user.get().getUserId());
        response.put("isSuccess",true);
        response.put("message",getUpdatedAttendanceList);
        }
        return  response;
    }

    public HashMap<String,Object> getNoOfDaysPresentWithSpecificUser(GetWorkingDaysDto getWorkingDaysDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();

        LocalDate start = YearMonth.now().atDay(1);
        LocalDate end   = YearMonth.now().atEndOfMonth();
        LocalDate date=LocalDate.now();
        LocalDate startDate=YearMonth.now().atDay(1);
        int day = date.getDayOfMonth();

//        List<Attendance> attendances=attendanceRepository.findByDateBetween(start.atStartOfDay(),end.atStartOfDay());
        List<Attendance> attendances = attendanceRepository.findByUserIdAndDateBetween(getWorkingDaysDto.getUserId(), start.atStartOfDay(), end.atStartOfDay());
        if (!attendances.isEmpty()) {
            List<AttendanceDaysDto> attendanceDaysDtoList = new ArrayList<>();
            int weekOfDays = 0;
            for (int i = 1; i <= day; i++) {
                if (startDate.getDayOfWeek() == DayOfWeek.SATURDAY || startDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    weekOfDays++;
                    startDate = startDate.plusDays(i);
                } else {
                    startDate = startDate.plusDays(i);
                }
            }
//            if ( isWorkingDay(start.atStartOfDay())){
            for (Attendance attendance : attendances) {
                AttendanceDaysDto attendanceDaysDto = new AttendanceDaysDto();
                attendanceDaysDto.setId(attendance.getId());
                attendanceDaysDto.setUserId(attendance.getUserId());
                attendanceDaysDto.setAttendanceStatus(attendance.getAttendanceStatus());
                attendanceDaysDto.setDate(attendance.getDate());
                attendanceDaysDto.setInTime(attendance.getInTime());
                attendanceDaysDto.setOutTime(attendance.getOutTime());
                attendanceDaysDtoList.add(attendanceDaysDto);
            }
//            }
            getWorkingDaysDto.setAttendancePresentDaysDtoList(attendanceDaysDtoList);
            getWorkingDaysDto.setCountOfPresentDays(attendanceDaysDtoList.stream().filter(presentDay ->presentDay.getAttendanceStatus().equalsIgnoreCase("Present") &&
                            presentDay.getInTime().getDayOfWeek() != DayOfWeek.SATURDAY && presentDay.getInTime().getDayOfWeek() != DayOfWeek.SUNDAY)
                    .collect(Collectors.toList()).stream().count()
            );
            getWorkingDaysDto.setCountOfAbsentDays(date.getDayOfMonth() - getWorkingDaysDto.getCountOfPresentDays() - weekOfDays);
            getWorkingDaysDto.setTotalWorkingDaysInMonth(end.lengthOfMonth());
            getWorkingDaysDto.setCountOfWeekEndDays(weekOfDays);
            getWorkingDaysDto.setCountOfLeaves(attendanceDaysDtoList.stream().filter(leaveDay-> leaveDay.getAttendanceStatus().equalsIgnoreCase("Approved")).count());
            response.put("isSuccess", true);
            response.put("message", getWorkingDaysDto);
        } else {
            response1.put("message", "incorrect User id " + getWorkingDaysDto.getUserId());
            response.put("isSuccess", false);
            response.put("message", response1);
        }
        return response;
    }

//    public  boolean isWorkingDay(LocalDateTime date){
//        DayOfWeek dayOfWeek=date.getDayOfWeek();
//        return dayOfWeek!=DayOfWeek.SATURDAY && dayOfWeek!=DayOfWeek.SUNDAY;
//    }


}