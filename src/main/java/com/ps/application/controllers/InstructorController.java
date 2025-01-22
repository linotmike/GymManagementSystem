package com.ps.application.controllers;

import com.ps.application.data.InstructorDao;
import com.ps.application.models.Instructors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
//@PreAuthorize("hasRole('ROLE_Admin')or hasRole('ROLE_Instructor')")

public class InstructorController {
    private InstructorDao instructorDao;

    @Autowired
    public InstructorController(InstructorDao instructorDao){
        this.instructorDao = instructorDao;
    }

    @GetMapping("{instructorId}")
    public ResponseEntity<?> getInstructorById(@PathVariable int instructorId){
        Instructors instructors = instructorDao.getInstructorById(instructorId);
        return ResponseEntity.ok(instructors);
    }

    @GetMapping
    public ResponseEntity<?> getAllInstructors(){
        List<Instructors>instructors = instructorDao.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }
}
