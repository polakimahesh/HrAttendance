package com.example.HrAttendance.Security;

import com.example.HrAttendance.Users.Users;
import lombok.Data;

@Data
public class JwtAuthRequest {
    Users users = new Users();
    private  String userName;
    private String password;
}
