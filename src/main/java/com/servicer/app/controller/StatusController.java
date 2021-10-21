package com.servicer.app.controller;

import com.servicer.app.model.Status;
import com.servicer.app.repository.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
@AllArgsConstructor
public class StatusController {
    private final StatusRepository statusRepository;


    @PostMapping("/")
    public ResponseEntity<?> postStatus(Status status){

        statusRepository.save(status);
        return ResponseEntity.ok(Arrays.asList(status.getId(),status.getName()));

    }
    @PutMapping("/")
    public ResponseEntity<?> putStatus(Status status){

        statusRepository.save(status);
        return ResponseEntity.ok(status.getName());

    }
}
