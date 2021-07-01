package com.interview.code.controller;

import com.interview.code.domain.Greeting;
import com.interview.code.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class InterviewController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping(path = "/get/{id}")
    public ResponseEntity<?> getInterview(@PathVariable Integer id) {
        return greetingService.getGreetingById(id);
    }
}
