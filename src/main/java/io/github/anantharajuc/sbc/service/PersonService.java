package io.github.anantharajuc.sbc.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.github.anantharajuc.sbc.model.Person;

public interface PersonService 
{
	List<Person> getAllPersons();
	
	Person getPersonById(Long id);
	
	Person savePerson(Person person);
	
	ResponseEntity<?> delete(Long id);
	
	Person updatePerson(Long personId, Person personDetails);
}
