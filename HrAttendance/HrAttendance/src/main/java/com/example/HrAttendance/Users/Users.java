package com.example.HrAttendance.Users;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
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
    @NotBlank
    private String name;
    @NotBlank
    private String designation;
    @NotNull
    @Column(unique = true)
    private  long mobileNo;
    @Email
    @Column(unique = true)
    private String email;
    @NotBlank
    private String department;

    private Boolean isAdmin;

}
