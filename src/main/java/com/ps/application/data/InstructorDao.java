package com.ps.application.data;

import com.ps.application.models.Instructors;

import java.util.List;

public interface InstructorDao {
    List<Instructors> getAllInstructors();
    Instructors getInstructorById (int instructorId);
    Instructors createInstructors (Instructors instructors);
    Instructors updateInstructors(int instructorId, Instructors instructors);
    Instructors deleteInstructors (int instructorId);
}
