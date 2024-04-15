package com.project.controller;

import com.project.model.UserForm;
import com.project.repository.LoginRepository;
import com.project.service.GradingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class GradingController {

  @Autowired
  private GradingService gradingService;

  @Autowired
  private LoginRepository loginRepository;

  @GetMapping("/login")
  public String login(Model model) {
    return "login";
  }

  @GetMapping("/admin")
  public String admin(Model model) {
    return "admin";
  }

  @GetMapping("/addUser")
  public String addUser(Model model) {
    model.addAttribute("userForm", new UserForm());
    return "addUser";
  }

  @PostMapping("/login")
  public String processLogin(@RequestParam String username, @RequestParam String password, Model model,
                             RedirectAttributes redirectAttributes) {
    return gradingService.authenticateUserAndRedirect(username, password, model, redirectAttributes);
  }

  @PostMapping("/addUser")
  public String addUser(@ModelAttribute("userForm") UserForm userForm, RedirectAttributes redirectAttributes) {
    log.info("User added :: " + userForm.toString());
    gradingService.addUser(userForm);
    redirectAttributes.addFlashAttribute("successMessage", "User successfully added!");
    return "redirect:/admin";
  }

  @GetMapping(value = "/viewAllUsers",
        produces = MediaType.APPLICATION_JSON_VALUE)
  public ModelAndView getUsers() {
    ModelAndView modelAndView = new ModelAndView("viewAllUsers");
    List<UserForm> usersList = loginRepository.findAll();
    log.info(usersList.toString());
    modelAndView.addObject("users", usersList);
    return modelAndView;
  }

  @PostMapping(value = "/searchStudents",
        produces = MediaType.APPLICATION_JSON_VALUE)
  public String getStudentsByClassAndExam(@RequestParam String message, @RequestParam String teacherClass, @RequestParam String teachingSubject,
                                          @RequestParam String classBelongTo, @RequestParam String examType,
                                          Model model) {
    List<UserForm> studentsList = loginRepository.findAllByClassBelongToAndExamType(classBelongTo, examType);
    log.info(studentsList.toString());
    if (!studentsList.isEmpty()) {
      model.addAttribute("message", message);
      System.out.println(teacherClass);
      model.addAttribute("teacherClass", teacherClass);
      model.addAttribute("teachingSubject", teachingSubject);
      model.addAttribute("students", studentsList);
    } else {
      model.addAttribute("errorMessage", "No data found");
    }
    return "teacher";
  }

  @RequestMapping(value = "/updateStudent/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
  public String getStudentsByClassAndExam(@PathVariable String id, Model model) {
    Optional<UserForm> repositoryById = loginRepository.findById(Long.valueOf(id));
    UserForm userForm = repositoryById.get();
    model.addAttribute("student", userForm);
    return "editStudent";
  }

  @PostMapping("/updateStudent")
  public String updateStudent(@ModelAttribute("userForm") UserForm userForm) {
    log.info("User added :: " + userForm.toString());
    gradingService.addUser(userForm);
    return "teacher";
//    return "admin";
  }
}
