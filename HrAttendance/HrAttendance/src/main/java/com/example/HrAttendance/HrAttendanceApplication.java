package com.example.HrAttendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;

@SpringBootApplication
public class HrAttendanceApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrAttendanceApplication.class, args);


	}



}
