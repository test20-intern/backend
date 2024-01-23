package com.ceylinco.salesapp.repository;

import com.ceylinco.salesapp.entity.EMPLOYEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EMPLOYEE, Long> {

    @Query("SELECT e.branchCode FROM EMPLOYEE e JOIN BRANCHES b ON e.branchCode = b.branchCode WHERE b.branchName = :branchName")
    Long findBranchCodeByBranchName(@Param("branchName") String branchName);

    // Add other query methods if needed
}

