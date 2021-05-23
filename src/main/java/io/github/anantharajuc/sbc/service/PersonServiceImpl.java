package io.github.anantharajuc.sbc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbc.exception.ResourceNotFoundException;
import io.github.anantharajuc.sbc.model.Person;
import io.github.anantharajuc.sbc.repository.PersonRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@CacheConfig(cacheNames={"person"}) // tells Spring where to store cache for this class
public class PersonServiceImpl implements PersonService
{
	@Autowired
	PersonRepository personRepository;
	
	@Cacheable 
	//The getAllPersons() call will first check the cache, "person" before actually invoking the method and then caching the result.
	//If the "person" cache contains the required result, the result is returned and the method is not invoked.
	public List<Person> getAllPersons() 
	{
		log.info("-----> getAllPersons service");
		
		simulateSlowService();
		
        return personRepository.findAll();
    }
	
	public Person getPersonById(Long id) 
	{
		log.info("-----> getPersonById service");
		
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));
    }
	
	@CacheEvict(allEntries = true) 
	//@CacheEvict annotation is used to indicate the removal of one or more/all values so that fresh values can be loaded into the cache again.
	public Person savePerson(Person person) 
	{
		log.info("-----> saveOrUpdate service");
		
        return personRepository.save(person);
    }
	
	@CacheEvict(allEntries = true)
	//@CacheEvict annotation is used to indicate the removal of one or more/all values so that fresh values can be loaded into the cache again.
	public ResponseEntity<?> delete(Long id) 
	{
		log.info("-----> delete service");
		
		Person person = personRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));
		
		personRepository.delete(person); 
		
		return ResponseEntity
				.ok()
				.build();
    }

	@Override
	@CacheEvict(allEntries = true)
	//@CacheEvict annotation is used to indicate the removal of one or more/all values so that fresh values can be loaded into the cache again.
	public Person updatePerson(Long id, Person personDetails) 
	{
		log.info("-----> updatePerson service");
		
		Person person = personRepository.findById(id)
							.orElseThrow(() -> new ResourceNotFoundException("Person", "id", id));
		
		person.setName(personDetails.getName());
		person.setEmail(personDetails.getEmail()); 
		person.setMobileNumber(personDetails.getMobileNumber()); 
		
		return personRepository.save(person);
	}
	
	private void simulateSlowService() 
	{		
        try 
        {
        	log.info("Deliberately simulating slowness for 3 seconds, indicative of a time consuming service/business logic.");
        	
            Thread.sleep(3000L);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }
}
