package com.alik.springcrud.services;

import com.alik.springcrud.models.Person;
import com.alik.springcrud.repositories.PeopleRepository;
import com.alik.springcrud.repositories.PeopleWithPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    PeopleRepository peopleRepository;
    PeopleWithPages peopleWithPages;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository,PeopleWithPages peopleWithPages) {
        this.peopleRepository = peopleRepository;
        this.peopleWithPages= peopleWithPages;
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

    public  List<Person> findByNameStartingWith(String startingWith){
        return peopleRepository.findByNameStartingWith(startingWith);
    }

    public Page<Person> findByPage(int pageNumber, int sizeOnPage){
        PageRequest pr = PageRequest.of(pageNumber, sizeOnPage, Sort.by("age"));
        return peopleWithPages.findAll(pr);
    }




}
