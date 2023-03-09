package com.example.HrAttendance.Users;

import com.example.HrAttendance.Dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;
    //get all the users
    @GetMapping("/employees")
    public ResponseEntity<List<Users>> getAllUsers(){
        return new ResponseEntity<>(usersService.getAllUsers(), HttpStatus.OK);
    }
    //create user
    @PostMapping("/register-employee")
    public ResponseEntity<Users> createUsers(@Valid @RequestBody UsersDto usersDto){
        return  new ResponseEntity<>(usersService.createUsers(usersDto),HttpStatus.CREATED);
    }
    //get single user with specific id
    @GetMapping("{id}")
    public ResponseEntity<Object> getSingleUser(@PathVariable int id){
        var user=usersService.getSingleUser(id);
        if(Boolean.TRUE.equals(user.get("isSuccess"))){
            return ResponseEntity.ok(user.get("message"));
        }else
            return ResponseEntity.badRequest().body(user.get("message"));
    }
    //update user with specific user id
    @PostMapping("/update-user")
    public  ResponseEntity<Object> updateUser(@RequestBody UserUpdateDto userUpdateDto){
        var user =usersService.updateUser(userUpdateDto);
        if(Boolean.TRUE.equals(user.get("isSuccess"))){
            return ResponseEntity.ok(user.get("message"));
        }else
            return ResponseEntity.badRequest().body(user.get("message"));
    }
    //delete user with specific user id
    @PostMapping("/delete-user")
    public  ResponseEntity<Object> deleteUser(@RequestBody DeleteUserDto deleteUserDto){
        var user=usersService.deleteUser(deleteUserDto);
        if(Boolean.TRUE.equals(user.get("isSuccess"))){
            return ResponseEntity.ok(user.get("message"));
        }else
            return ResponseEntity.badRequest().body(user.get("message"));
    }

    @PostMapping("/admin-attendance-response")
    public ResponseEntity<Object> adminAttendanceResponse(@RequestBody AdminResponseDto adminResponseDto){
        var adminResponse=usersService.adminAttendanceResponse(adminResponseDto);
        if(Boolean.TRUE.equals(adminResponse.get("isSuccess"))){
            return ResponseEntity.ok(adminResponse.get("message"));
        }else
            return ResponseEntity.badRequest().body(adminResponse.get("message"));
    }
    @PostMapping("/admin-leaves-response")
    public  ResponseEntity<Object> adminResponseByUserLeaves(@RequestBody LeavesResponseDto leavesResponseDto){
        var adminResponseForLeaves =usersService.adminResponseByUserLeaves(leavesResponseDto);
        if(Boolean.TRUE.equals(adminResponseForLeaves.get("isSuccess"))){
            return ResponseEntity.ok(adminResponseForLeaves.get("message"));
        }else
            return ResponseEntity.badRequest().body(adminResponseForLeaves.get("message"));
    }
}
