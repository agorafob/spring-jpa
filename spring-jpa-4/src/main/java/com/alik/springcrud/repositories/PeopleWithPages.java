package com.alik.springcrud.repositories;

import com.alik.springcrud.models.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleWithPages extends PagingAndSortingRepository<Person,Integer> {
}
