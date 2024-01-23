package com.ceylinco.salesapp.service;

import com.ceylinco.salesapp.dto.EmployeeFormDTO;
import com.ceylinco.salesapp.entity.EMPLOYEE;
import com.ceylinco.salesapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveEmployee(EmployeeFormDTO employeeFormDTO) {
        String branchName = employeeFormDTO.getBranchName();
        Long branchCode = employeeRepository.findBranchCodeByBranchName(branchName);

        if (branchCode != null) {
            EMPLOYEE employee = new EMPLOYEE();
            employee.setDob(employeeFormDTO.getDob());
            employee.setEmpNo(employeeFormDTO.getEmpNo());
            employee.setName(employeeFormDTO.getName());
            employee.setStatus(employeeFormDTO.getStatus());
            employee.setBranchCode(String.valueOf(branchCode));

            employeeRepository.save(employee);
        } else {
            // Handle the case where branchCode is null
            System.err.println("Branch code is null for branch name: " + branchName);
            // You might want to throw an exception or log the error
        }
    }
}
