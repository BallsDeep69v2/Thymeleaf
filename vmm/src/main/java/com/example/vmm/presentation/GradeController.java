package com.example.vmm.presentation;

import com.example.vmm.domain.Grade;
import com.example.vmm.persistence.GradeRepository;
import com.example.vmm.persistence.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("students")
public record GradeController(GradeRepository gradeRepository, StudentRepository studentRepository) {

    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "Students";
    }

    @GetMapping("/{id}")
    public String getStudentGrades(@PathVariable String id, Model model) {
        model.addAttribute("student", studentRepository.findById(id).orElseThrow());
        model.addAttribute("grades", gradeRepository.getAllGradesByStudentId(id));
        model.addAttribute("newgrade", new Grade());
        return "student_grades";
    }

    @PostMapping("/{id}")
    public String redirectAfterSaving(@PathVariable String id, @Valid @ModelAttribute("newgrade") Grade grade) {
        grade.setStudent(studentRepository.findById(id).orElseThrow());
        gradeRepository.save(grade);
        return "redirect:/students/" + id;
    }

    @GetMapping("/error")
    public String redirectToErrorPage(){
        return "error";
    }

}
