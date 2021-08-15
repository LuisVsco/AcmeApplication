package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}