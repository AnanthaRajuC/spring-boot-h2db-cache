package io.github.anantharajuc.springbooth2db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.anantharajuc.springbooth2db.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>
{

}
