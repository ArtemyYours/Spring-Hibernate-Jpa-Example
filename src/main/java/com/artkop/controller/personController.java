package com.artkop.controller;

import com.artkop.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class personController {

    @GetMapping("/create")
    @ResponseBody
    public Person helloPeople(){

        Person person=new Person();
        person.setId(1L);
        person.setName("Artem");

        return person;
    }
}
