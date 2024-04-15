package com.project.service;

import com.project.model.UserForm;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface GradingService {
  UserForm addUser(UserForm userForm);

  String authenticateUserAndRedirect(String username, String password, Model model, RedirectAttributes redirectAttributes);
}
