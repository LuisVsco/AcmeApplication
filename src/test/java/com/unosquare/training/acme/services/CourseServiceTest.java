package com.unosquare.training.acme.services;

import com.unosquare.training.acme.dto.CourseDto;
import com.unosquare.training.acme.dto.UserDto;
import com.unosquare.training.acme.model.Course;
import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private CourseService courseService;

    private CourseDto SetCourseDto(final String status, final String role) {
        UserDto user = new UserDto();

        user.setId(1);
        user.setName("Silvester");
        user.setUsername("silver_s");
        user.setSchool("cucei");
        user.setRole(role);

        CourseDto courseDto = new CourseDto();
        courseDto.setId(1);
        courseDto.setName("Programming I");
        courseDto.setStatus(status);
        courseDto.setUser(user);

        return courseDto;
    }

    private Course SetCourseEntity() {
        User user = new User();
        user.setId(1);
        user.setName("Silvester");
        user.setUsername("silver_s");
        user.setSchool("cucei");
        user.setRole("instructor");

        Course course = new Course();
        course.setName("Programming I");
        course.setStatus("created");
        course.setId(1);
        course.setUser(user);
        return course;
    }

    private Course SetCourseEntityToModify(final String status) {
        User user = new User();
        user.setId(1);
        user.setName("Silvester");
        user.setUsername("silver_s");
        user.setSchool("cucei");
        user.setRole("instructor");

        Course course = new Course();
        course.setName("Programming I");
        course.setStatus(status);
        course.setId(1);
        course.setUser(user);
        return course;
    }

    private UserDto SetUserDto(String role) {
        UserDto user = new UserDto();

        user.setId(1);
        user.setName("Silvester");
        user.setUsername("silver_s");
        user.setSchool("cucei");
        user.setRole(role);
        return user;
    }

    @Test
    void saveCourse() {
        Mockito.when(courseRepository.save(ArgumentMatchers.any(Course.class))).thenReturn(SetCourseEntity());
        CourseDto resp = courseService.SaveCourse(SetCourseDto("created", "instructor"));
        Assertions.assertEquals(1, resp.getId());
        Assertions.assertEquals("Programming I", resp.getName());
    }

    @Test
    void getCourseById() {
        Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(SetCourseEntity()));
        Optional<Course> optionalCourse = Optional.ofNullable(courseService.GetCourseById(1));
        Assertions.assertTrue(optionalCourse.isPresent());
        Assertions.assertEquals("created", optionalCourse.get().getStatus());
    }

    @Test
    void getAllCoursesByStudent() {
        List<Course> courseListExp = Arrays.asList(SetCourseEntity());
        Mockito.when(userService.GetUserById(1)).thenReturn(SetUserDto("student"));
        Mockito.when(courseRepository.findByNotStatus("canceled")).thenReturn(courseListExp);
        List<CourseDto> courses = courseService.GetAllCoursesByStudent(1);
        Assertions.assertEquals("Programming I", courses.get(0).getName());
    }

    @Test
    void getAllCoursesByInstructor() {
        List<Course> courseListExp = Arrays.asList(SetCourseEntity());
        Mockito.when(userService.GetUserById(1)).thenReturn(SetUserDto("instructor"));
        Mockito.when(courseRepository.findByUserId(1)).thenReturn(courseListExp);
        List<CourseDto> courses = courseService.GetAllCoursesByInstructor(1);
        Assertions.assertEquals("Programming I", courses.get(0).getName());
    }
}