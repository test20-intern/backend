package com.ceylinco.salesapp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EMPLOYEE {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private Date dob;
//    private String empNo;
//    private String name;
//    private String status;
//
//    @ManyToOne
//    @JoinColumn(name = "branchCode", referencedColumnName = "branchCode")
//    private BRANCHES branch;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String empNo;

    private String name;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;

    private String status;

    @ManyToOne
    @JoinColumn(name = "branchCode", referencedColumnName = "branchCode")
    private BRANCHES branch;

    // Other fields and methods...

    // Generate employee number starting with 'E'
    @PrePersist
    public void generateEmployeeNumber() {
        if (this.empNo == null || this.empNo.isEmpty()) {
            // Logic to generate employee number with a numeric part
            this.empNo = "E" + generateNumericPart(); // Update this line accordingly
        }
    }

    // Generate the numeric part of the employee number
    private String generateNumericPart() {
        // Use a sequence, database-generated ID, or any other mechanism to generate a unique numeric part
        // In this example, a simple counter is used. You may need to adapt it based on your persistence strategy.
        // Make sure to handle concurrency appropriately in a production environment.
        Long nextEmployeeId = getNextEmployeeId(); // Implement this method to get the next employee ID
        return nextEmployeeId.toString();
    }

    // Placeholder method for getting the next employee ID (you need to implement it)
    private Long getNextEmployeeId() {
        // Implement your logic to get the next employee ID (e.g., from a sequence, database, etc.)
        // This is a placeholder, and you need to adapt it based on your database or sequence strategy.
        // Make sure to handle concurrency appropriately in a production environment.
        // For simplicity, a static counter is used in this example.
        return Counter.getNext();
    }

    // A simple counter class for demonstration purposes
    private static class Counter {
        private static long count = 0;

        public static synchronized long getNext() {
            return ++count;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BRANCHES getBranch() {
        return branch;
    }

    public void setBranch(BRANCHES branch) {
        this.branch = branch;
    }
}
