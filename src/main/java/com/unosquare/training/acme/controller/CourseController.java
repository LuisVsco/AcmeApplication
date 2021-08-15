package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping(value = "/course")
    public void SaveCourse(@RequestBody Course course) {
        courseService.SaveCourse(course);
    }
}
