package io.github.anantharajuc.sbc.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbc.model.Person;
import io.github.anantharajuc.sbc.model.dto.PersonDTO;
import io.github.anantharajuc.sbc.repository.PersonRepository;
import io.github.anantharajuc.sbc.service.PersonServiceImpl;

@RestController
@RequestMapping("/api/")
public class PersonCommandController 
{
	@Autowired
	PersonServiceImpl personServiceImpl;
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	ModelMapper modelMapper;

	/**
     * Create/Update a person. 
     * Returned person will have the auto-generated id if its newly created.
     * 
     * @param person. The person to create
     * @return the created person
     */
	@PostMapping(path="/person", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
    public Person savePerson(@RequestBody PersonDTO personDTO) 
	{
		Person person = modelMapper.map(personDTO, Person.class);
				
        personServiceImpl.savePerson(person);
        
        return person; 
    }
	
	/**
     * Update a person. Hero must exist for id.
     * 
     * @param id. The id of the person to be updated
     * @param person. The person values to be updated
     * @throws ResourceNotFoundException if person not found.
     */
	@PutMapping(value = "/person/{id}", consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Person updatePerson(@PathVariable("id") Long id, @Valid @RequestBody PersonDTO personDTO)
	{
		Person person = modelMapper.map(personDTO, Person.class);
		
		return personServiceImpl.updatePerson(id, person);	
	}
	
	/**
     * Delete Person
     * 
     * @param id. The persons id.
     * @throws ResourceNotFoundException if the person is not found.
     */
	@DeleteMapping("/person/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) 
	{
		return personServiceImpl.delete(id);
    }
} 
