package com.example.HrAttendance.Attendance;

import com.example.HrAttendance.Attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceUserRepository extends JpaRepository<AttendanceUser,Integer> {
//    @Query(value = "select * from attendanceUser a where Date(a.date) = :date and a.user_id =:userId", nativeQuery = true)
//    AttendanceUser findByUserIdAndDate(@Param("userId") int userID, @Param("date") LocalDate date);

    List<AttendanceUser> findByUserId(int userId);

}
