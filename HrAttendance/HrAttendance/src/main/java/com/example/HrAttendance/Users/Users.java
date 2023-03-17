package com.example.HrAttendance.Users;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

    private String name;

    private String designation;

    @Column(unique = true)
    private  long mobileNo;

    @Column(unique = true)
    private String email;

    private String department;

    @Column(columnDefinition = "boolean default false")
    private Boolean isAdmin;



}
