package com.bridgelabz.mockitodemo.person.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.mockitodemo.person.models.Person;
import com.bridgelabz.mockitodemo.person.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person addPerson(Person person) {
		person.setCreatedAt(LocalDateTime.now());
		return personRepository.save(person);
	}
	
	public void updateSalary(int salary, long id) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		if(!optionalPerson.isPresent()) {
			throw new RuntimeException("Person does not exist");
		}
		
		Person person = optionalPerson.get();
		person.setSalary(salary);
		
		personRepository.save(person);
	}

	public void deletePerson(long id) {
		personRepository.deleteById(id);
	}

	public Person getById(long id) {
		Optional<Person> optionalPerson = personRepository.findById(id);
		
		if(!optionalPerson.isPresent()) {
			throw new RuntimeException("Person not found");
		}
		
		return optionalPerson.get();
	}

}
