package com.unosquare.training.acme.services;

import com.unosquare.training.acme.model.User;
import com.unosquare.training.acme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User SaveUser(User user) {
        return userRepository.save(user);
    }

    public User GetUserById(final Integer id) {
        return userRepository.findById(id)
                .orElseThrow(EntityExistsException::new);
    }
}
