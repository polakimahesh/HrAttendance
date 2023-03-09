package com.example.HrAttendance.Dto;

import com.example.HrAttendance.Validation.ValidateEmployeeMobileNumber;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@Getter
@Setter
public class UsersDto {
    @NotBlank(message = "name is mandatory")
    private String usersName;
    @NotBlank(message = "designation is mandatory")
    private String usersDesignation;

//    @Range(min=10000000,max =1000000000 ,message="mobile no is mandatory")
//    @NotNull(message = "Please enter mobile number!")
    //custom validation
    @ValidateEmployeeMobileNumber(message = "Please Enter 10 digit mobile number")
    private  long usersMobileNo;
    @Email(message="Enter a valid email id")
    private String usersEmail;
    @NotBlank(message = "department name is mandatory")
    private String usersDepartment;
    private Boolean isAdmin;

}
