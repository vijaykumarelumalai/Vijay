package com.example.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.login.model.Student;
import com.example.login.service.StudentService;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        if (studentService.authenticate(username, password)) {
            Student student = studentService.findByUsername(username);
            session.setAttribute("student", student);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        if (studentService.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        Student newStudent = new Student();
        newStudent.setUsername(username);
        newStudent.setEmail(email);
        newStudent.setPassword(password);
        studentService.saveStudent(newStudent);
        return "redirect:/login?registered=true";
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }
        model.addAttribute("student", student);
        return "dashboard";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}