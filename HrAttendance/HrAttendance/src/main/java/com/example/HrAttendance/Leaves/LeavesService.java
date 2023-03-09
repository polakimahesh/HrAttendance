package com.example.HrAttendance.Leaves;

import com.example.HrAttendance.Dto.GetAllLeavesDto;
import com.example.HrAttendance.Dto.LeavesListDto;
import com.example.HrAttendance.Dto.LeavesRequestDto;
import com.example.HrAttendance.Users.Users;
import com.example.HrAttendance.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LeavesService {
    @Autowired
    private LeavesRepository leavesRepository;
    @Autowired
    private UsersRepository usersRepository;

    public List<Leaves> getListOfAllLeavesTakenByUsers(){
        return leavesRepository.findAll();
    }

    @Transactional
    public HashMap<String,Object> userRequestForLeaves(LeavesRequestDto leavesRequestDto) {
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> response1 = new HashMap<>();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Users users= usersRepository.findById(leavesRequestDto.getUserId()).orElse(null);
        if(users!=null){
            List<Leaves> leaves = leavesRepository.findByUserId(leavesRequestDto.getUserId());
            if (leaves == null || leavesRequestDto.getDate() != null) {
                Leaves leave=new Leaves();
                leave.setUserId(leavesRequestDto.getUserId());
                leave.setDate(LocalDate.parse(leavesRequestDto.getDate().format(dateTimeFormatter)));
                leave.setReason(leavesRequestDto.getReason());
                leavesRepository.save(leave);
                response.put("isSuccess", true);
                response.put("message", leave);
            } else {
                response1.put("message", "already update " + leavesRequestDto.getUserId() + ", id!");
                response.put("isSuccess", false);
                response.put("message", response1);
            }
            return response;
        }
        response1.put("message","Incorrect userid "+ leavesRequestDto.getUserId());
        response.put("isSuccess",false);
        response.put("message",response1);
        return response;
    }

    @Transactional
    public HashMap<String,Object> getListOfLeavesWithSpecificUser(GetAllLeavesDto getAllLeavesDto){
        HashMap<String, Object> response = new HashMap<>();
        HashMap<String, Object> response1 = new HashMap<>();
        var user=usersRepository.findById(getAllLeavesDto.getUserId());
        if(user.isEmpty()){
            response1.put("message","incorrect User id "+ getAllLeavesDto.getUserId());
            response.put("isSuccess",false);
            response.put("message",response1);
        }else {
            List<Leaves> leaves=leavesRepository.findByUserId(getAllLeavesDto.getUserId());
            List<LeavesListDto> leavesListDtos=new ArrayList<>();
            for(Leaves leave:leaves){
                LeavesListDto leavesListDto=new LeavesListDto();
                leavesListDto.setLeaveId(leave.getId());
                leavesListDto.setUserId(leave.getUserId());
                leavesListDto.setDate(leave.getDate());
                leavesListDto.setReason(leave.getReason());
                leavesListDto.setRequestCreatedOn(leave.getCreatedAt());
                leavesListDtos.add(leavesListDto);
            }
            getAllLeavesDto.setLeavesListDtos(leavesListDtos);
            response.put("isSuccess",true);
            response.put("message",getAllLeavesDto);
        }
        return  response;
    }

}
