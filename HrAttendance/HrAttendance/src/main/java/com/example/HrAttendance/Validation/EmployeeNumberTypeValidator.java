package com.example.HrAttendance.Validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmployeeNumberTypeValidator implements ConstraintValidator<ValidateEmployeeMobileNumber,Long> {

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(value==null){
            return true;
        }else{
            String mobileNumberString= Long.toString(value);
            return mobileNumberString.matches("^[0-9]{10}$");
        }
    }
}
