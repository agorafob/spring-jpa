package com.alik.springcrud.dao;

 import com.alik.springcrud.models.Item;
 import com.alik.springcrud.models.Person;
 import jakarta.persistence.Access;
 import jakarta.persistence.EntityManager;
 import org.hibernate.Session;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
 import java.util.List;

@Component
public class PersonDAO {
    private EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1(){
        Session session = entityManager.unwrap(Session.class);
        // n + 1
//        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();
//        for (Person p :people  ) {
//            System.out.println("Person " + p.getName() + " has " + p.getItems());
//        }
        //solution
//        List<Person> people = session.createQuery("select p from Person p left join fetch p.items").getResultList();
//        for (Person p :people  ) {
//            System.out.println("Person " + p.getName() + " has " + p.getItems());
//        }
    }
    @Transactional(readOnly = true)
    public void testLoad(){
        Session session = entityManager.unwrap(Session.class);
        Person p = session.getReference(Person.class, 2);
        System.out.println("loaded person");
        Item i = new Item("new item");
        System.out.println("create item");
        i.setOwner(p);
        System.out.println("owner set");
        System.out.println(i);

    }
}
