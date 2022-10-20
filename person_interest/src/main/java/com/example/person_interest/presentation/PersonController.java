package com.example.person_interest.presentation;

import com.example.person_interest.domain.Person;
import com.example.person_interest.domain.Sex;
import com.example.person_interest.persistence.InterestRepository;
import com.example.person_interest.persistence.PersonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping
public record PersonController(PersonRepository personRepository, InterestRepository interestRepository) {

    @GetMapping("/persons")
    public String getOverviewOfStudents(Model model) {
        model.addAttribute("all_persons", personRepository.findAll());
        return "persons_overview";
    }

    @GetMapping("/persons/new")
    public String addNewPerson(Model model) {
        if (!model.containsAttribute("new_person"))
            model.addAttribute("new_person", new Person());
        model.addAttribute("allSexes", Sex.values());
        model.addAttribute("allInterests", interestRepository.findAll());
        return "add_new_person";
    }

    @PostMapping("/persons/new")
    public String saveNewPerson(@Valid @ModelAttribute("new_person") Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return addNewPerson(model);
        }
        personRepository.save(person);
        return "redirect:/persons";
    }

    @GetMapping("/error")
    public String redirectToError(){
        return "error";
    }
}
