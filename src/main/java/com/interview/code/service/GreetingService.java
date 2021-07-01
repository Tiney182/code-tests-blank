package com.interview.code.service;

import com.interview.code.domain.Greeting;
import com.interview.code.domain.GreetingRepository;
import com.interview.code.domain.GreetingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

    public ResponseEntity<?> getGreetingById(Integer id) {
        Optional<Greeting> byId = greetingRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(GreetingResponse.builder()
                    .value(byId.get().getValue())
                    .build());
        }
        return ResponseEntity.notFound().build();
    }
}
