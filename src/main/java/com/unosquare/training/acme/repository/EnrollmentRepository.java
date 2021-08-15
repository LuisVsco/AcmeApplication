package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
}