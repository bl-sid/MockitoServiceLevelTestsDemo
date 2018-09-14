package com.bridgelabz.mockitodemo.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.mockitodemo.person.models.Person;
import com.bridgelabz.mockitodemo.person.services.PersonService;

@RestController
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@PostMapping("/")
	public void add(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@PutMapping("update/{id}/{salary}")
	public void updateSalary(@PathVariable long id, @PathVariable int salary) {
		personService.updateSalary(salary, id);
	}
	
	@DeleteMapping("/{id}")
	public void delerePerson(@PathVariable long id) {
		personService.deletePerson(id);
	}
	
	@GetMapping("/{id}")
	public Person getPerson(@PathVariable long id) {
		return personService.getById(id);
	}
}
