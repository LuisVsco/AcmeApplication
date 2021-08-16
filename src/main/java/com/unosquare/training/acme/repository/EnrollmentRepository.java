package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    @Query(value = "SELECT * FROM ENROLLMENT WHERE STUDENT_ID=?1",nativeQuery = true)
    List<Enrollment>findByStudentId(Integer id);
    @Query(value = "SELECT * FROM ENROLLMENT join COURSE on COURSE.ID = ENROLLMENT.COURSE_ID join USER as STUDENT on STUDENT.ID = ENROLLMENT.STUDENT_ID WHERE COURSE.USER_ID = ?1",nativeQuery = true)
    List<Enrollment>ListStudentsEnrolledInCourse(Integer id);
}