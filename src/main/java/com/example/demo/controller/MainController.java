package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class MainController {
    private PersonRepository personRepository;

    public MainController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void fillDB(){
        if(this.personRepository.count() == 0){
            this.personRepository.save(new Person("Youri", "Van Laer"));
            this.personRepository.save(new Person("KATO", "Van Laer"));
            this.personRepository.save(new Person("HEID", "Van Laer"));
            this.personRepository.save(new Person("LORENZO", "Van Laer"));
        }

        System.out.println("Fill db");
    }
}
