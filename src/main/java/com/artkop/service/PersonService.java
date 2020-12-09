package com.artkop.service;

import com.artkop.model.Person;
import com.artkop.repository.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepo repository;

    public List<Person> persons(){
        return repository.findAll();
    }

}
