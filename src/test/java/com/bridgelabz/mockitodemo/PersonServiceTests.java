package com.bridgelabz.mockitodemo;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bridgelabz.mockitodemo.person.models.Person;
import com.bridgelabz.mockitodemo.person.repositories.PersonRepository;
import com.bridgelabz.mockitodemo.person.services.PersonService;

public class PersonServiceTests {

	@InjectMocks
	private PersonService personService;

	@Mock
	private PersonRepository personRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addPersonTest() {
		when(personRepository.save(any(Person.class))).thenReturn(new Person());

		Person person = new Person();

		assertThat(personService.addPerson(person), is(notNullValue()));
	}

	@Test
	public void updatePersonSalaryTest() {
		when(personRepository.findById(1L)).thenReturn(Optional.<Person>of(new Person()));
		when(personRepository.save(any(Person.class))).thenReturn(new Person());

		personService.updateSalary(16000, 1);

		verify(personRepository, times(1)).findById(1L);

		verify(personRepository, times(1)).save(any(Person.class));
	}

	@Test(expected = RuntimeException.class)
	public void updatePersonSalaryFailedTest() {
		when(personRepository.findById(-1L)).thenReturn(Optional.<Person>empty());
		when(personRepository.save(any(Person.class))).thenReturn(new Person());

		doThrow(RuntimeException.class).when(personRepository).findById(-1L);

		personService.updateSalary(16000, -1);

		verify(personRepository, times(1)).findById(1L);
	}
	
	@Test
	public void deletePersonSalaryTest() {
		personService.deletePerson(1L);

		verify(personRepository, times(1)).deleteById(1L);

	}
	
	@Test
	public void getByIdTest() {
		Person p = new Person();
		p.setId(1);
		when(personRepository.findById(1L)).thenReturn(Optional.<Person>of(p));
		
		Person person = personService.getById(1);
		
		assertEquals(person.getId(), p.getId());
		
		verify(personRepository, times(1)).findById(1L);
	}
	
	@Test(expected=RuntimeException.class)
	public void getByIdFailedTest() {
		when(personRepository.findById(-1L)).thenReturn(Optional.<Person>empty());
		
		personService.getById(1L);
		
		verify(personRepository, times(1)).findById(1L);
	}

}
