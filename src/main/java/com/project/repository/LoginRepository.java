package com.project.repository;

import com.project.model.UserForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserForm, Long> {

  Optional<UserForm> findByEmailAndPassword(String email, String password);

  List<UserForm> findAllByClassBelongToAndExamType(String classBelongTo, String examType);

  List<UserForm> findAllByEmailAndPassword(String username, String password);

}
