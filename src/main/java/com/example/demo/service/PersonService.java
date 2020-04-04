package com.example.demo.service;

import com.example.demo.entity.Contact;
import com.example.demo.entity.Person;
import com.example.demo.model.PersonModel;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PersonService {


    PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Set<Contact> getContactOfPerson(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);
        Person person = personOptional.orElseThrow();

        return person.getContactSet();
    }


    public void addPerson(PersonModel personModel){
        Person person = new Person();
        if(personModel.getUserEmail() != null){
            User user = userRepository.findUserByEmail(personModel.getUserEmail()).orElseThrow();
            person.setUser(user);
        }
        person.setFirstName(personModel.getFirstName());
        person.setLastName(personModel.getLastName());
        personRepository.save(person);
    }

}

