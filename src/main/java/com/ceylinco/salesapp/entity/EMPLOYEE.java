package com.ceylinco.salesapp.entity;

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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date dob;
    private String empNo;
    private String name;
    private String status;

    @ManyToOne
    @JoinColumn(name = "branchCode", referencedColumnName = "branchCode")
    private BRANCHES branch;


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
