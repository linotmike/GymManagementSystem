package com.ps.application.controllers;

import com.ps.application.data.InstructorDao;
import com.ps.application.models.Instructors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
//    @PostMapping
//    public ResponseEntity<?>createInstructors(@RequestBody Instructors instructors){
//        Instructors newInstructors = instructorDao.createInstructors(instructors);
//        return ResponseEntity.ok(newInstructors);
//    }
@PostMapping
public ResponseEntity<?> createInstructors(@RequestBody Instructors instructors) {
    try {
        Instructors newInstructor = instructorDao.createInstructors(instructors);
        if (newInstructor == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create instructor. Possible duplicate user.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newInstructor);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the instructor.");
    }
}

}
