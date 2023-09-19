package com.alik.springcrud.services;

import com.alik.springcrud.models.Person;
import com.alik.springcrud.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
        return peopleRepository.findAll();
    }


    public Person findOne(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        return person.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        person.setCreatedAt(new Date());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Person> findByName(String name){
        return peopleRepository.findByName(name);
    }

    public List<Person> findByNameOrderByAge(String name){
        return peopleRepository.findByNameOrderByAge(name);
    }

    public List<Person> findByEmail(String email){
        return peopleRepository.findByEmail(email);
    }

    public  List<Person> findByNameStartingWith(String startingWith){
        return peopleRepository.findByNameStartingWith(startingWith);
    }




}
