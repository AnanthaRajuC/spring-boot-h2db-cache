package io.github.anantharajuc.springbooth2db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.springbooth2db.model.Person;
import io.github.anantharajuc.springbooth2db.service.PersonServiceImpl;

@RestController
@RequestMapping("/Persons")
public class PersonController 
{
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@GetMapping(path="/all", produces = "application/json")
    private List<Person> getAllPersons() 
	{
        return personServiceImpl.getAllPersons();
    }
	
	@GetMapping("/{id}")
    private Person getPerson(@PathVariable("id") Long id) 
	{
        return personServiceImpl.getPersonById(id);
    }
	
	@PostMapping("/persons")
    private Long savePerson(@RequestBody Person person) 
	{
        personServiceImpl.saveOrUpdate(person);
        return person.getId();
    }
	
	@DeleteMapping("/persons/{id}")
    private void deletePerson(@PathVariable("id") Long id) 
	{
        personServiceImpl.delete(id);
    }
} 
