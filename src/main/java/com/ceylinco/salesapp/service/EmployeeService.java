package com.ceylinco.salesapp.service;

import com.ceylinco.salesapp.dto.EmployeeFormDTO;
import com.ceylinco.salesapp.entity.BRANCHES;
import com.ceylinco.salesapp.entity.EMPLOYEE;
import com.ceylinco.salesapp.repository.BranchesRepository;
import com.ceylinco.salesapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchesRepository branchRepository; // Correct variable name

    public void saveEmployee(EmployeeFormDTO employeeFormDTO) {
        String branchName = employeeFormDTO.getBranchName();
        BRANCHES branch = branchRepository.findByBranchName(branchName);

        if (branch != null) {
            EMPLOYEE employee = new EMPLOYEE();
            employee.setDob(employeeFormDTO.getDob());
            employee.setEmpNo(employeeFormDTO.getEmpNo());
            employee.setName(employeeFormDTO.getName());
            employee.setStatus(employeeFormDTO.getStatus());
            employee.setBranch(branch);

            employeeRepository.save(employee);
        } else {
            // Handle the case where branch is null
            System.err.println("Branch is null for branch name: " + branchName);
            // You might want to throw an exception or log the error
        }
    }
}

