package com.example.HrAttendance.Security;

import com.example.HrAttendance.Users.Users;
import com.example.HrAttendance.Users.UsersRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {

    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("mahesh")){
            return new User("mahesh","mahesh123",new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("user not found exception");
        }
    }
}
