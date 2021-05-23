package io.github.anantharajuc.sbc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.anantharajuc.sbc.api.APIutil;
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
    public ResponseEntity<List<Person>> getAllPersons(@RequestHeader(value=APIutil.HEADER_API_NAME, defaultValue="${api.name}") String apiName, 
    												  @RequestHeader(value=APIutil.HEADER_API_VERSION, defaultValue="${api.version}") String apiVersion) 
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 

		headers.add(APIutil.HEADER_API_NAME, apiName);
		headers.add(APIutil.HEADER_API_VERSION, apiVersion);
		
		return new ResponseEntity<>(personServiceImpl.getAllPersons(), headers, HttpStatus.OK);
    }
	
	/**
     * Get a Person by id.
     * 
     * @param id. The persons id         
     * @return the person
     */
	@GetMapping(value = "/person/{id}", produces = "application/json")
    public ResponseEntity<Person> getPerson(@RequestHeader(value=APIutil.HEADER_API_NAME, defaultValue="${api.name}") String apiName, 
    										@RequestHeader(value=APIutil.HEADER_API_VERSION, defaultValue="${api.version}") String apiVersion,
    										@PathVariable("id") Long id) 
	{
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>(); 

		headers.add(APIutil.HEADER_API_NAME, apiName);
		headers.add(APIutil.HEADER_API_VERSION, apiVersion);
		
        return new ResponseEntity<>(personServiceImpl.getPersonById(id), headers, HttpStatus.OK);
    }
}
