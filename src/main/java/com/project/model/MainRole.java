package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public enum MainRole {
  STUDENT("Student"),
  TEACHER("Teacher");

  private final String abbreviation;

  MainRole(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getAbbreviation() {
    return abbreviation;
  }
}
