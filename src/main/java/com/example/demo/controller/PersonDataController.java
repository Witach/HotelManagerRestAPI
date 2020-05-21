package com.example.demo.controller;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Person;
import com.example.demo.model.ContactModel;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.demo.config.Messages.*;

@Slf4j
@RestController
@RequestMapping("/people")
public class PersonDataController {

    //    @Autowired
//    public PersonDataController(ContactService contactService) {
//        this.contactService = contactService;
//    }
//
//    @Transactional
//    @PostMapping("/contact")
//    public ResponseEntity<?> createContact(@RequestBody @Valid ContactModel contact){
//        log.info(makeMessageForController("POST [/contact]"));
//        Contact newContact = contactService.addContact(contact);
//        Link link = new Link("/contact/"+ newContact.getId());
//        return ResponseEntity.created(link.toUri()).build();
//    }
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable long id) {
        var person = personRepository.findById(id);
        return ResponseEntity.ok(
                person.orElseThrow(IllegalArgumentException::new)
        );
    }

    @GetMapping
    public ResponseEntity<Page<Person>> getPeople(Pageable pageable) {
        var personPage = personRepository.findAll(pageable);
        return ResponseEntity.ok(personPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable long id) {
        if (!personRepository.existsById(id))
            throw new IllegalArgumentException("Person with this id doesn't exits");
        var newPerson = personRepository.save(person);
        return ResponseEntity.ok(newPerson);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
