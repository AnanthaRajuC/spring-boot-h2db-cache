package io.github.anantharajuc.sbc.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbc.api.APIutil;
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
    public ResponseEntity<Person> savePerson(@RequestHeader(value=APIutil.HEADER_API_NAME, defaultValue="${api.name}") String apiName, 
			  								 @RequestHeader(value=APIutil.HEADER_API_VERSION, defaultValue="${api.version}") String apiVersion,
			                                 @RequestBody PersonDTO personDTO) 
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 

		headers.add(APIutil.HEADER_API_NAME, apiName);
		headers.add(APIutil.HEADER_API_VERSION, apiVersion);
		
		Person person = modelMapper.map(personDTO, Person.class);
				
		return new ResponseEntity<>(personServiceImpl.savePerson(person), headers, HttpStatus.CREATED);
    }
	
	/**
     * Update a person. Hero must exist for id.
     * 
     * @param id. The id of the person to be updated
     * @param person. The person values to be updated
     * @throws ResourceNotFoundException if person not found.
     */
	@PutMapping(value = "/person/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Person> updatePerson(@RequestHeader(value=APIutil.HEADER_API_NAME, defaultValue="${api.name}") String apiName, 
				 							   @RequestHeader(value=APIutil.HEADER_API_VERSION, defaultValue="${api.version}") String apiVersion,
			                                   @PathVariable("id") Long id, @Valid @RequestBody PersonDTO personDTO)
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 

		headers.add(APIutil.HEADER_API_NAME, apiName);
		headers.add(APIutil.HEADER_API_VERSION, apiVersion);
		
		Person person = modelMapper.map(personDTO, Person.class);
		
		return new ResponseEntity<>(personServiceImpl.updatePerson(id, person), headers, HttpStatus.NO_CONTENT);	
	}
	
	/**
     * Delete Person
     * 
     * @param id. The persons id.
     * @throws ResourceNotFoundException if the person is not found.
     */
	@DeleteMapping("/person/{id}")
    public ResponseEntity<?> deletePerson(@RequestHeader(value=APIutil.HEADER_API_NAME, defaultValue="${api.name}") String apiName, 
			   							  @RequestHeader(value=APIutil.HEADER_API_VERSION, defaultValue="${api.version}") String apiVersion,
			   							  @PathVariable("id") Long id) 
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 

		headers.add(APIutil.HEADER_API_NAME, apiName);
		headers.add(APIutil.HEADER_API_VERSION, apiVersion);
		
		return new ResponseEntity<>(personServiceImpl.delete(id), headers, HttpStatus.NO_CONTENT);
    }
} 
