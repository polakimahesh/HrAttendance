package com.example.HrAttendance.Dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAllLeavesDto {
    private int userId;
    private List<LeavesListDto> leavesListDtos;
}
