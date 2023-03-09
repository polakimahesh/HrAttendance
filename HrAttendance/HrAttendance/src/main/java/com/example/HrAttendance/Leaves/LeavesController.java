package com.example.HrAttendance.Leaves;

import com.example.HrAttendance.Dto.GetAllLeavesDto;
import com.example.HrAttendance.Dto.LeavesRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaves")
public class LeavesController {
    @Autowired
    private LeavesService leavesService;
    @GetMapping("/get-all-requested-leaves")
    public ResponseEntity<Object> getListOfAllLeavesTakenByUsers(){
        return new ResponseEntity<>(leavesService.getListOfAllLeavesTakenByUsers(), HttpStatus.OK);
    }
    @PostMapping("/user-request-for-leave")
    public ResponseEntity<Object> userRequestForLeaves(@RequestBody LeavesRequestDto leavesRequestDto){
        var leaves=leavesService.userRequestForLeaves(leavesRequestDto);
        if(Boolean.TRUE.equals(leaves.get("isSuccess"))){
            return ResponseEntity.ok(leaves.get("message"));
        }else
            return ResponseEntity.badRequest().body(leaves.get("message"));
    }
    @PostMapping("/user-leaves-with-specific-id")
    public ResponseEntity<Object> getListOfLeavesWithSpecificUser(@RequestBody GetAllLeavesDto getAllLeavesDto){
        var leaves=leavesService.getListOfLeavesWithSpecificUser(getAllLeavesDto);
        if(Boolean.TRUE.equals(leaves.get("isSuccess"))){
            return ResponseEntity.ok(leaves.get("message"));
        }else
            return ResponseEntity.badRequest().body(leaves.get("message"));
    }
}
