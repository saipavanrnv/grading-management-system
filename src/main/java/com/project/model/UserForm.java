package com.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_grading")
public class UserForm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String classBelongTo;
  private String teachingSubject;
  private String role;
  private String examType;
  private String maths;
  private String science;
  private String social;
  private String regionalLanguage;
  private String computerSciences;
}
