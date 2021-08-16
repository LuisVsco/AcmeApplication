package com.unosquare.training.acme.repository;

import com.unosquare.training.acme.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT * FROM USER WHERE USERNAME=?1",nativeQuery = true)
    User getUserByUserName(String userName);
}