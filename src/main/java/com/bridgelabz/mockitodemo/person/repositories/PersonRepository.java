package com.bridgelabz.mockitodemo.person.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.mockitodemo.person.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
