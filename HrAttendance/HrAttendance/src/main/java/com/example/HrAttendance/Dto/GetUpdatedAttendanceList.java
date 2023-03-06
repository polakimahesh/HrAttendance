package com.example.HrAttendance.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetUpdatedAttendanceList {
    private int userId;
    private List<UpdatedAttendanceListDto> updatedAttendanceListDtos;
}
