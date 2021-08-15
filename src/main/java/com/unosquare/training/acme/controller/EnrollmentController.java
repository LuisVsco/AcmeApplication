package com.unosquare.training.acme.controller;

import com.unosquare.training.acme.dto.EnrollmentDto;
import com.unosquare.training.acme.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping(value = "/enrollment")
    public void SaveEnrollment(@RequestBody EnrollmentDto enrollmentDto){
        enrollmentService.SaveEnrollment(enrollmentDto);
    }
}
