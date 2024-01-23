package com.ceylinco.salesapp.controller;

import com.ceylinco.salesapp.dto.EmployeeFormDTO;
import com.ceylinco.salesapp.entity.EMPLOYEE;
import com.ceylinco.salesapp.repository.EmployeeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Data
@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<EMPLOYEE> getAllEmployee(){
        return employeeRepository.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<EMPLOYEE> addEmployee(@RequestBody EmployeeFormDTO employeeFormDTO) {
        try {
            // Use branchCode directly from the form
            String branchCode = employeeFormDTO.getBranchCode();

            // Create EMPLOYEE object
            EMPLOYEE employee = new EMPLOYEE();
            employee.setEmpNo(employeeFormDTO.getEmpNo());
            employee.setDob(employeeFormDTO.getDob());
            employee.setName(employeeFormDTO.getName());
            employee.setStatus(employeeFormDTO.getStatus());
            employee.setBranchCode(branchCode); // Set the branchCode

            // Save the employee
            EMPLOYEE savedEmployee = employeeRepository.save(employee);

            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees/{empNo}")
    public ResponseEntity<EMPLOYEE> getEmployeeByID(@PathVariable(value = "EMPNO") Long EMPNO) {
        Optional<EMPLOYEE> employeeOptional = employeeRepository.findById(EMPNO);
        return employeeOptional.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/employees/{empNo}")
    public ResponseEntity<String> updateEmployee(@PathVariable(value = "empNo") Long empNo, @RequestBody EMPLOYEE updatedEmployee) {
        Optional<EMPLOYEE> existingEmployeeOptional = employeeRepository.findById(empNo);

        if (existingEmployeeOptional.isPresent()) {
            EMPLOYEE existingEmployee = existingEmployeeOptional.get();
            existingEmployee.setBranchCode(updatedEmployee.getBranchCode());
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setDob(updatedEmployee.getDob());
            existingEmployee.setStatus(updatedEmployee.getStatus());

            try {
                employeeRepository.save(existingEmployee);
                return ResponseEntity.ok("Employee updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Failed to update employee");
            }
        } else {
            return ResponseEntity.status(404).body("Employee not found");
        }
    }
}
