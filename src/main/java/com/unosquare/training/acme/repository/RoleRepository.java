package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}