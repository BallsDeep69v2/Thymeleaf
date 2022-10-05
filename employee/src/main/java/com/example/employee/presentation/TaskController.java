package com.example.employee.presentation;

import com.example.employee.persistence.EmployeeRepository;
import com.example.employee.persistence.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public record TaskController(TaskRepository taskRepository, EmployeeRepository employeeRepository) {

    @GetMapping("/staff")
    public String allEmployees(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
        return "employees-list";
    }

    @GetMapping("/tasks")
    public String allTasksForEmployee(@RequestParam(name = "employee") String id, Model model) {
        model.addAttribute("tasks", taskRepository.allTasksByEmployee(id));
        return "tasks-list";
    }
}