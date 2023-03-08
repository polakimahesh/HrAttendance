package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UsersDto {
    @NotBlank(message = "name is mandatory")
    private String usersName;
    @NotBlank(message = "designation is mandatory")
    private String usersDesignation;
    @NotNull
    @Range(min = 100,max = 100000000,message="mobile no is mandatory")
    private  long usersMobileNo;
    @Email(message="Enter a valid email id")
    private String usersEmail;
    @NotBlank(message = "department name is mandatory")
    private String usersDepartment;
    private Boolean isAdmin;

}
