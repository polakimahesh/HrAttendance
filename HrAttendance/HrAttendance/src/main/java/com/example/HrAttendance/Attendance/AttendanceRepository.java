package com.example.HrAttendance.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Integer> {
    List<Attendance> findByUserId(int userId);
    @Query(value = "select * from attendance a where Date(a.date) = :date and a.user_id =:userId", nativeQuery = true)
    Attendance findByUserIdAndDate(@Param("userId") int userID,@Param("date") LocalDate date);



}
