package com.ceylinco.salesapp.controller;

import com.ceylinco.salesapp.entity.BRANCHES;
import com.ceylinco.salesapp.repository.BranchesRepository;
import com.ceylinco.salesapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class BranchesController {

    @Autowired
    private BranchesRepository branchesRepository;


    @GetMapping("/branches")
    public List<BRANCHES> getAllBranches() {
        try {
            return branchesRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


}
