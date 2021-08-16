package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.Course;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "SELECT * FROM COURSE WHERE USER_ID=?1",nativeQuery = true)
    List<Course> findByUserId(Integer id);
    @Query(value = "SELECT * FROM COURSE WHERE NOT STATUS=?1",nativeQuery = true)
    List<Course> findByNotStatus(String status);
}