package com.example.HrAttendance.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmployeeNumberTypeValidator.class)
public @interface ValidateEmployeeMobileNumber {
    String message() default "Invalid mobile number";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
