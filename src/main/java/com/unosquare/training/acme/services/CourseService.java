package com.unosquare.training.acme.services;

import com.unosquare.training.acme.dto.CourseDto;
import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.CourseRepository;
import com.unosquare.training.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserService userService;

    public void SaveCourse(CourseDto courseDto) {
        if (courseDto.getUser().getRole().equalsIgnoreCase("instructor")) {
            User instructor = new User();
            instructor.setId(courseDto.getUser().getId());
            instructor.setSchool(courseDto.getUser().getSchool());
            instructor.setName(courseDto.getUser().getName());
            instructor.setRole(courseDto.getUser().getRole());
            Course course = new Course();
            course.setName(courseDto.getName());
            course.setStatus(courseDto.getStatus());
            course.setUser(instructor);
            course.setStartDate(courseDto.getStartDate());
            course.setCreationDate(courseDto.getCreationDate());
            courseRepository.save(course);
        }
    }

    public Course GetCourseById(final Integer idCourse) {
        return courseRepository.findById(idCourse).orElseThrow(EntityNotFoundException::new);
    }

    public List<CourseDto> GetAllCoursesByStudent(final Integer id) {
        String role = userService.GetUserById(id).getRole();
        if (role.equalsIgnoreCase("student")) {
            List<CourseDto> courses = new ArrayList<>();
            List<Course> resp = courseRepository.findByNotStatus("canceled");
            for (Course course : resp) {
                CourseDto courseDto = new CourseDto();
                UserDto instructor = new UserDto();

                instructor.setId(course.getUser().getId());
                instructor.setName(course.getUser().getName());
                instructor.setSchool(course.getUser().getSchool());
                instructor.setRole(course.getUser().getRole());

                courseDto.setId(course.getId());
                courseDto.setName(course.getName());
                courseDto.setCreationDate(course.getCreationDate());
                courseDto.setStartDate(course.getStartDate());
                courseDto.setStatus(course.getStatus());
                courseDto.setUser(instructor);
                courses.add(courseDto);
            }
            return courses;
        }
        return new ArrayList<>();
    }

    public List<CourseDto> GetAllCoursesByInstructor(final Integer id) {
        String role = userService.GetUserById(id).getRole();
        if (role.equalsIgnoreCase("instructor")) {
            List<CourseDto> courses = new ArrayList<>();
            List<Course> resp = courseRepository.findByUserId(id);
            for (Course course : resp) {
                CourseDto courseDto = new CourseDto();
                UserDto instructor = new UserDto();

                instructor.setId(course.getUser().getId());
                instructor.setName(course.getUser().getName());
                instructor.setSchool(course.getUser().getSchool());
                instructor.setRole(course.getUser().getRole());

                courseDto.setId(course.getId());
                courseDto.setName(course.getName());
                courseDto.setCreationDate(course.getCreationDate());
                courseDto.setStartDate(course.getStartDate());
                courseDto.setStatus(course.getStatus());
                courseDto.setUser(instructor);
                courses.add(courseDto);
            }
            return courses;
        }
        return new ArrayList<>();
    }

    public void StartCourse(final Integer courseId, final Integer instructorId) {
        String role = userService.GetUserById(instructorId).getRole();
        if (role.equalsIgnoreCase("instructor")) {
            Optional<Course> courseToUpdate = courseRepository.findById(courseId);
            if (courseToUpdate.isPresent()) {
                Course course = courseToUpdate.get();
                course.setStatus("started");
                courseRepository.save(course);
            }
        }
    }

    public void CancelCourse(final Integer courseId, final Integer instructorId) {
        String role = userService.GetUserById(instructorId).getRole();
        if (role.equalsIgnoreCase("instructor")) {
            Optional<Course> courseToUpdate = courseRepository.findById(courseId);
            if (courseToUpdate.isPresent() && courseToUpdate.get().getStatus().equalsIgnoreCase("created")) {
                Course course = courseToUpdate.get();
                course.setStatus("canceled");
                courseRepository.save(course);
            }
        }
    }
}
