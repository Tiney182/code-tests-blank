package com.interview.code.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterviewController {

    @GetMapping(path = "/get/{name}")
    public ResponseEntity<String> getInterview(@PathVariable String name) {
        return ResponseEntity.ok("Hello " + name);
    }
}
