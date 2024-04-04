package com.project.controller;

import com.project.model.LoginResponseRequest;
import com.project.repository.GradingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GradingController {

  @Autowired
  private GradingRepository gradingRepository;

  @GetMapping(value = "/all-login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<LoginResponseRequest> getData(@RequestBody LoginResponseRequest request) {
    gradingRepository.findByEmail(request)
  }
}
