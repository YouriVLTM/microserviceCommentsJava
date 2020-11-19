package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class index {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping("/getAllPersons")
    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
}
