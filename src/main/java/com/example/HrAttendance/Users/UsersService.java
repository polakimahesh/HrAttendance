package com.example.HrAttendance.Users;


import com.example.HrAttendance.Dto.DeleteUserDto;
import com.example.HrAttendance.Dto.UserUpdateDto;
import com.example.HrAttendance.Dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;



@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }
    public Users createUsers(UsersDto usersDto){
        Users users = new Users();
        users.setName(usersDto.getUsersName());
        users.setDesignation(usersDto.getUsersDesignation());
        users.setMobileNo(usersDto.getUsersMobileNo());
        users.setEmail(usersDto.getUsersEmail());
        users.setDepartment(usersDto.getUsersDepartment());
        usersRepository.save(users);
        return  users;
    }
    public HashMap<String,Object> getSingleUser(int id){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users = usersRepository.findById(id);
        if(users.isEmpty()){
            response1.put("message","incorrect Employee id "+id+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }
        response.put("isSuccess",true);
        response.put("message",users);
        return  response;
    }

    public HashMap<String,Object> updateUser(UserUpdateDto userUpdateDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users = usersRepository.findById(userUpdateDto.getUsersId()).orElse(null);
        if(users==null){
            response1.put("message","incorrect Employee id "+userUpdateDto.getUsersId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {
            users.setName(userUpdateDto.getName());
            users.setDepartment(userUpdateDto.getDepartment());
            users.setDesignation(userUpdateDto.getDesignation());
            users.setEmail(userUpdateDto.getEmail());
            users.setMobileNo(userUpdateDto.getMobileNo());
            usersRepository.save(users);
            response1.put("message","Update Employee id "+userUpdateDto.getUsersId()+", Successfully!");
            response.put("isSuccess",true);
            response.put("message",users);
            return  response;
        }
    }

    public  HashMap<String,Object> deleteUser(DeleteUserDto deleteUserDto){
        HashMap<String,Object> response = new HashMap<>();
        HashMap<String,Object> response1= new HashMap<>();
        var users = usersRepository.findById(deleteUserDto.getUserId()).orElse(null);
        if(users==null){
            response1.put("message","incorrect Employee id "+deleteUserDto.getUserId()+", please enter the valid id!");
            response.put("isSuccess",false);
            response.put("message",response1);
            return  response;
        }else {
            usersRepository.deleteById(deleteUserDto.getUserId());
            response1.put("message","User deleted successfully! "+deleteUserDto.getUserId());
            response.put("isSuccess",true);
            response.put("message",response1);
            return  response;
        }
    }
}
