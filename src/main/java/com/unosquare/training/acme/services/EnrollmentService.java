package com.unosquare.training.acme.services;

import com.unosquare.training.acme.dto.EnrollmentDto;
import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.model.Enrollment;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CourseService courseService;

    public void SaveEnrollment(final EnrollmentDto enrollmentDto) {
        User student = userService.GetUserById(enrollmentDto.getStudent());
        Course course = courseService.GetCourseById(enrollmentDto.getCourse());

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        if (!enrollment.getCourse().getStatus().equalsIgnoreCase("started")) {
            enrollmentRepository.save(enrollment);
        }
    }
}
