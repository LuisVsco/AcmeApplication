package com.unosquare.training.acme.services;

import com.unosquare.training.acme.dto.CourseDto;
import com.unosquare.training.acme.dto.EnrollmentDto;
import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.model.Enrollment;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.EnrollmentRepository;
import com.unosquare.training.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseService courseService;

    public void SaveEnrollment(final EnrollmentDto enrollmentDto) {
        Optional<User> student = userRepository.findById(enrollmentDto.getStudent());
        Course course = courseService.GetCourseById(enrollmentDto.getCourse());
        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student.get());
        enrollment.setCourse(course);

        if (!enrollment.getCourse().getStatus().equalsIgnoreCase("started")) {
            enrollmentRepository.save(enrollment);
        }
    }

    public void DeleteEnrollment(final Integer enrollmentId) {
        enrollmentRepository.deleteById(enrollmentId);
    }

    public List<CourseDto> GetEnrollmentByStudent(final Integer studentId) {
        List<Enrollment> enrollmentList = enrollmentRepository.findByStudentId(studentId);
        List<CourseDto> coursesResoponse = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            Course tmp = enrollment.getCourse();
            CourseDto dto = new CourseDto();
            dto.setId(tmp.getId());
            dto.setName(tmp.getName());
            dto.setStatus(tmp.getStatus());
            dto.setCreationDate(tmp.getCreationDate());
            dto.setStartDate(tmp.getStartDate());
            coursesResoponse.add(dto);
        }
        return coursesResoponse;
    }

    public List<UserDto> ListStudentWithInstructor(final Integer id) {
        List<Enrollment> enrollmentList = enrollmentRepository.ListStudentsEnrolledInCourse(id);
        List<UserDto> students = new ArrayList<>();
        for (Enrollment enrollment : enrollmentList) {
            User tmp = enrollment.getStudent();
            UserDto dto = new UserDto();
            dto.setId(tmp.getId());
            dto.setName(tmp.getName());
            dto.setSchool(tmp.getSchool());
            students.add(dto);
        }
        return students;
    }
}
