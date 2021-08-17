package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.dto.CourseDto;
import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/course")
    public ResponseEntity<Object> SaveCourse(@RequestBody CourseDto course) {
        CourseDto savedCourse=  courseService.SaveCourse(course);
        if (savedCourse.getId()!=null){
            return ResponseEntity.ok().body(savedCourse);
        }
        return ResponseEntity.badRequest().body("You have no rights to do this");
    }

    @GetMapping(value = "/list/course/student")
    public List<CourseDto> ListCourses(@RequestParam Integer id) {
        return courseService.GetAllCoursesByStudent(id);
    }

    @GetMapping(value = "/list/course/instructor")
    public List<CourseDto> ListCoursesByInstructor(@RequestParam Integer id) {
        return courseService.GetAllCoursesByInstructor(id);
    }

    @PutMapping(value = "/start/course")
    public String StartCourse(@RequestParam final Integer courseId, @RequestParam final Integer instructorId) {
        courseService.StartCourse(courseId, instructorId);
        return "ok";
    }

    @PutMapping(value = "/cancel/course")
    public String CancelCourse(@RequestParam final Integer courseId, @RequestParam final Integer instructorId) {
        courseService.CancelCourse(courseId, instructorId);
        return "ok";
    }

}
