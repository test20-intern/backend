package com.ceylinco.salesapp.repository;

import com.ceylinco.salesapp.entity.EMPLOYEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EMPLOYEE, Long> {
    @Query("SELECT b.branchCode FROM EMPLOYEE e JOIN e.branch b WHERE b.branchName = :branchName")
    Long findBranchCodeByBranchName(@Param("branchName") String branchName);
}


