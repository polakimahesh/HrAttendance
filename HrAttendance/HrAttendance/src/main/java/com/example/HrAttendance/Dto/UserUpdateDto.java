package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdateDto{
    private  int usersId;
    private  String name;
    private String designation;
    private  long mobileNo;
    private String department;
}
