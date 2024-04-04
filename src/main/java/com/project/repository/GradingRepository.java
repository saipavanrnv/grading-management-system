package com.project.repository;

import com.project.model.LoginResponseRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradingRepository extends JpaRepository<LoginResponseRequest, Long> {

  LoginResponseRequest findByEmail(String email);
}
