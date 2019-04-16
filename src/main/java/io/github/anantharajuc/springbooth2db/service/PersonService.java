package io.github.anantharajuc.springbooth2db.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.springbooth2db.model.Person;

@Service
public interface PersonService 
{
	List<Person> getAllPersons();
	
	Person getPersonById(Long id);
	
	void saveOrUpdate(Person person);
	
	ResponseEntity delete(Long id);
}
