package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}