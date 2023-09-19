package com.alik.springcrud.controllers;

import com.alik.springcrud.models.Person;
import com.alik.springcrud.services.ItemsService;
import com.alik.springcrud.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;
    private final ItemsService itemsService;

    @Autowired
    public PeopleController(PeopleService peopleService, ItemsService itemsService) {
        this.peopleService = peopleService;
        this.itemsService = itemsService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());

        System.out.println(itemsService.findByItemName("airpods"));
        System.out.println(itemsService.findByOwner(peopleService.findAll().get(6)));

        System.out.println(peopleService.findByName("Tom"));
        System.out.println(peopleService.findByNameOrderByAge("Bob"));
        System.out.println(peopleService.findByEmail("bob1@gmail.com"));
        System.out.println(peopleService.findByNameStartingWith("B"));


        return "/people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "/people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @ModelAttribute("headerMessage")
    public String headerMessage(){
        return "Welcome to website";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,@PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "/people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }


}
