package com.example.HrAttendance.Users;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "port_gen", sequenceName = "port_gen",  initialValue = 1001, allocationSize = 1)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "port_gen")
    private  int id;
    @NotBlank
    private String name;
    @NotBlank
    private String designation;
    private  long mobileNo;
    @Email
    private String email;
    private String department;

}
