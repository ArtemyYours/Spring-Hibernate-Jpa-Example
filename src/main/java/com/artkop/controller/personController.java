package com.artkop.controller;

import com.artkop.model.Person;
import com.artkop.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor
public class personController {
    PersonService personService;

    @GetMapping("/create")
    @ResponseBody
    public List<Person> helloPeople(){
        return personService.persons();
    }
}
