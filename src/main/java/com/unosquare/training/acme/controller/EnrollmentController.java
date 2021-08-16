package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.dto.CourseDto;
import com.unosquare.training.acme.dto.EnrollmentDto;
import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping(value = "/enrollment")
    public void SaveEnrollment(@RequestBody EnrollmentDto enrollmentDto) {
        enrollmentService.SaveEnrollment(enrollmentDto);
    }

    @DeleteMapping(value = "/drop/enrollment")
    public void DeleteEnrollment(@RequestBody final Integer enrollmentId) {
        enrollmentService.DeleteEnrollment(enrollmentId);
    }

    @GetMapping(value = "/student/enrollment")
    public List<CourseDto> StudentListEnrollment(@RequestParam final Integer id) {
        return enrollmentService.GetEnrollmentByStudent(id);
    }

    @GetMapping(value = "/student/enrollment/instructor")
    public List<UserDto> ListStudentWithInstructor(@RequestParam final Integer id) {
        return enrollmentService.ListStudentWithInstructor(id);
    }
}
