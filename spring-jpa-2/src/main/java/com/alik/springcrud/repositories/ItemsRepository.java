package com.alik.springcrud.repositories;

import com.alik.springcrud.models.Item;
import com.alik.springcrud.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item,Integer> {


    List<Item> findByItemName(String itemName);

    List<Item> findByOwner(Person owner);
}
