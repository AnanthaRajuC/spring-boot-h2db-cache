package io.github.anantharajuc.springbooth2db.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.github.anantharajuc.springbooth2db.model.Person;
import io.github.anantharajuc.springbooth2db.repository.PersonRepository;

@Service
@CacheConfig(cacheNames={"users"}) // tells Spring where to store cache for this class
public class PersonServiceImpl implements PersonService
{
	@Autowired
	PersonRepository personRepository;
	
	@Cacheable // caches the result of getAllPersons() method
	public List<Person> getAllPersons() 
	{
		simulateSlowService();
        return personRepository.findAll();
    }
	
	public Person getPersonById(Long id) 
	{
        return personRepository.findById(id).get();
    }
	
	@CacheEvict(allEntries = true) 
	public void saveOrUpdate(Person person) 
	{
        personRepository.save(person);
    }
	
	@CacheEvict(allEntries = true)
	public ResponseEntity<?> delete(Long id) 
	{
        personRepository.deleteById(id);
        return new ResponseEntity<Object>(HttpStatus.OK);
    }
	
	private void simulateSlowService() 
	{
        try 
        {
            Thread.sleep(3000L);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }
}
