package com.example.HrAttendance.Users;


import com.example.HrAttendance.Attendance.Attendance;
import com.example.HrAttendance.Attendance.AttendanceRepository;
import com.example.HrAttendance.Attendance.AttendanceUserRepository;
import com.example.HrAttendance.Dto.AdminResponseDto;
import com.example.HrAttendance.Dto.DeleteUserDto;
import com.example.HrAttendance.Dto.UserUpdateDto;
import com.example.HrAttendance.Dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AttendanceUserRepository attendanceUserRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll().stream().sorted(Comparator.comparingInt(Users::getId)).collect(Collectors.toList());
    }
    public Users createUsers(UsersDto usersDto){
        Users users = new Users();
        users.setName(usersDto.getUsersName());
        users.setDesignation(usersDto.getUsersDesignation());
        users.setMobileNo(usersDto.getUsersMobileNo());
        users.setEmail(usersDto.getUsersEmail());
        users.setDepartment(usersDto.getUsersDepartment());
        users.setIsAdmin(usersDto.getIsAdmin());
        usersRepository.save(users);
        return  users;
    }
    public HashMap<String,Object> getSingleUser(int id){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users = usersRepository.findById(id);
        if(users.isEmpty()){
            response1.put("message","incorrect Employee id "+id+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }
        response.put("isSuccess",true);
        response.put("message",users);
        return  response;
    }

    public HashMap<String,Object> updateUser(UserUpdateDto userUpdateDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users = usersRepository.findById(userUpdateDto.getUsersId()).orElse(null);
        if(users==null){
            response1.put("message","incorrect Employee id "+userUpdateDto.getUsersId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {
            users.setName(userUpdateDto.getName());
            users.setDepartment(userUpdateDto.getDepartment());
            users.setDesignation(userUpdateDto.getDesignation());
            users.setEmail(userUpdateDto.getEmail());
            users.setMobileNo(userUpdateDto.getMobileNo());
            users.setIsAdmin(userUpdateDto.getIsAdmin());
            usersRepository.save(users);
            response1.put("message","Update Employee id "+userUpdateDto.getUsersId()+", Successfully!");
            response.put("isSuccess",true);
            response.put("message",users);
            return  response;
        }
    }

    public  HashMap<String,Object> deleteUser(DeleteUserDto deleteUserDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users = usersRepository.findById(deleteUserDto.getUserId()).orElse(null);
        if(users==null){
            response1.put("message","incorrect Employee id "+deleteUserDto.getUserId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {
            usersRepository.deleteById(deleteUserDto.getUserId());
            response1.put("message","User deleted successfully! "+deleteUserDto.getUserId());
            response.put("isSuccess",true);
            response.put("message",response1);
            return  response;
        }
    }

    public HashMap<String,Object> adminAttendanceResponse(AdminResponseDto adminResponseDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var user =usersRepository.findById(adminResponseDto.getUserId()).orElse(null);
        if(user==null){
            response1.put("message","incorrect Employee id "+adminResponseDto.getUserId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else{
            var attendanceRequest =attendanceUserRepository.findById(adminResponseDto.getAttendanceUserId()).orElse(null);
            if(user.getIsAdmin()){
                if(attendanceRequest==null){
                    response1.put("message","incorrect attendanceUser id "+adminResponseDto.getAttendanceUserId() +", please enter the valid id!");
                    response.put("isSuccess",false);
                    response.put("message",response1);
                }else{
                    LocalDateTime dateTime =LocalDateTime.now();
                    Admin admin =new Admin();
                    admin.setAdminName(user.getName());
                    admin.setStatus("Approved attendance id "+adminResponseDto.getAttendanceUserId() +" his employee id is "+adminResponseDto.getUserId() );
                    admin.setApprovedTime(dateTime);
                    adminRepository.save(admin);
                    Attendance attendance = new Attendance();
                    attendance.setUserId(attendanceRequest.getUserId()); // LocalDate.parse((attendanceUpdateDto.getDate().format(dateTimeFormatter)))
                    attendance.setInTime( (LocalDateTime.of(attendanceRequest.getDate(), LocalTime.of(9,30))));
                    attendance.setOutTime( (LocalDateTime.of(attendanceRequest.getDate(),LocalTime.of(18,30))));
                    attendance.setAttendanceStatus("Approved by "+user.getName());
                    attendance.setId(attendanceRequest.getId());
                    attendance.setDate(attendanceRequest.getDate().atStartOfDay());
                    attendanceRepository.save(attendance);
                    attendanceUserRepository.deleteById(attendanceRequest.getId());
                    response.put("isSuccess",true);
                    response.put("message",admin);
                }
                return  response;
            }
            response.put("isSuccess",false);
            response.put("message",user);
            return  response;
        }
    }
}
