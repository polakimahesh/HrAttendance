package com.example.HrAttendance.Leaves;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface LeavesRepository extends JpaRepository<Leaves, Integer> {
    List<Leaves> findByUserId(int userId);

}
