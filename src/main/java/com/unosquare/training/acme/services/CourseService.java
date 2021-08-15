package com.unosquare.training.acme.services;

import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public void SaveCourse(Course course) {
        if (course.getUser().getRole().getName().equalsIgnoreCase("instructor")) {
            courseRepository.save(course);
        }
    }
    public Course GetCourseById(final Integer idCourse){
        return courseRepository.findById(idCourse).orElseThrow(EntityNotFoundException::new);
    }
}
