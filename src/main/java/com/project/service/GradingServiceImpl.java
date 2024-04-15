package com.project.service;

import com.project.model.UserForm;
import com.project.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GradingServiceImpl implements GradingService {

  private LoginRepository loginRepository;

  @Autowired
  public GradingServiceImpl(LoginRepository loginRepository) {
    this.loginRepository = loginRepository;
  }

  @Override
  public UserForm addUser(UserForm userForm) {
    return loginRepository.save(userForm);
  }

  @Override
  public String authenticateUserAndRedirect(String username, String password, Model model,
                                            RedirectAttributes redirectAttributes) {
    // Your authentication logic here

    Optional<UserForm> userDetails = loginRepository.findByEmailAndPassword(username, password);
    if (userDetails.isPresent()) {
      // Assuming you have a method to get the user's role based on username
      String role = userDetails.get().getRole();

      // Redirect based on role
      if ("Admin".equalsIgnoreCase(role)) {
        model.addAttribute("message", "Hello ADMIN :: " + userDetails.get().getLastName());
        return "admin";
      } else if ("Teacher".equalsIgnoreCase(role)) {
        System.out.println(userDetails);
        model.addAttribute("message", "Hello TEACHER :: " + userDetails.get().getLastName());
        model.addAttribute("teacherClass", userDetails.get().getClassBelongTo());
        model.addAttribute("teachingSubject", userDetails.get().getTeachingSubject());
        return "teacher";
      } else if ("Student".equalsIgnoreCase(role)) {
        model.addAttribute("student", userDetails.get());
        model.addAttribute("message", "Hello STUDENT :: " + userDetails.get().getLastName());
        return "student";
      } else {
        // Handle invalid role or authentication failure
        redirectAttributes.addFlashAttribute("errorMessage", "User Role internal error");
        return "redirect:/login";
      }
    }  else {
      // Handle invalid role or authentication failure
      redirectAttributes.addFlashAttribute("errorMessage", "UserId/Password didn't match");
      return "redirect:/login";
    }
  }
}
