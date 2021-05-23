package io.github.anantharajuc.sbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbc.model.Person;
import io.github.anantharajuc.sbc.repository.PersonRepository;
import io.github.anantharajuc.sbc.service.PersonServiceImpl;

@RestController
@RequestMapping("/api/")
public class PersonQueryController 
{
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@Autowired
	PersonRepository personRepository;
	
	/**
     * Retrieve all persons
     * 
     * @return List<Person> with all persons
     */
	@GetMapping(path="/person", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
    public List<Person> getAllPersons() 
	{
        return personServiceImpl.getAllPersons();
    }
	
	/**
     * Get a Person by id.
     * 
     * @param id. The persons id         
     * @return the person
     */
	@GetMapping(value = "/person/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
    public Person getPerson(@PathVariable("id") Long id) 
	{
        return personServiceImpl.getPersonById(id);
    }
}
