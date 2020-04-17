package com.example.demo;

import com.example.demo.entity.*;
import com.example.demo.model.ContactModel;
import com.example.demo.model.PersonModel;
import com.example.demo.repository.*;
import com.example.demo.service.BillService;
import com.example.demo.service.ContactService;
import com.example.demo.service.DefaultUserDetailsService;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DemoApplicationTests {

    Reservation reservation;
    Person person;
    User user;
    Contact contact;
    Bill bill;

    BillRepository billRepository;
    UserRepository userRepository;
    ReservationRepository reservationRepository;
    ContactRepository contactRepository;
    PersonRepository personRepository;

    BillService billServiceMock;
    SecurityContext securityContextMock;

    @BeforeEach
    void initializeBeans() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);
        reservation.setFromDate(LocalDateTime.now());
        reservation.setToDate(LocalDateTime.now().minusWeeks(1L));
        Person person = new Person();
        person.setLastName("Witszek");
        person.setFirstName("Dawid");
        person.setId(1L);
        User user = new User();
        user.setEmail("admin");
        user.setPassword("{noop}admin");
        UserRole userRole = new UserRole();
        userRole.setId(0L);
        userRole.setName("USER");
        user.getRole().add(userRole);
        user.setId(1L);
        user.setPerson(person);
        person.setUser(user);
        Room room_a = new Room();
        room_a.setPrice(50.);
        room_a.setId(1L);
        Room room_b = new Room();
        room_b.setPrice(50.);
        room_b.setId(2L);
        reservation.setRoomSet(Set.of(room_a, room_b));
        Contact contact = new Contact();
        contact.setPerson(person);
        contact.setPhoneNumber("792343278");
        person.getContactSet().add(contact);
        Bill bill = new Bill();
        bill.setAdministrator(user);
        bill.setTenant(person);
        this.reservation = reservation;
        this.person = person;
        this.user = user;
        this.contact = contact;
        billRepository = mock(BillRepository.class);
        reservationRepository = mock(ReservationRepository.class);
        userRepository = mock(UserRepository.class);
        personRepository = mock(PersonRepository.class);
        contactRepository = mock(ContactRepository.class);
        securityContextMock = mock(SecurityContext.class);
        billServiceMock = mock(BillService.class);
    }

    @Test
    void shouldCreateBillFromReservationInfo() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(billRepository.save(any(Bill.class))).thenReturn(null);

        BillService billService = new BillService(billRepository, reservationRepository, userRepository);
        Bill bill = billService.createBillFromReservation(reservation, "admin");
        assertThat(bill.getTenant()).isEqualTo(person);
    }

    @Test
    void shouldCreateContact(){
        AtomicReference<Contact> newContact = new AtomicReference<>();
        when(contactRepository.save(any())).thenAnswer(
                invocationn -> {
                    newContact.set(invocationn.getArgument(0));
                    return invocationn.getArgument(0);
                }
        );
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));

        ContactModel contactModel = new ContactModel();
        contactModel.setPersonId(1L);
        contactModel.setPhoneNumber("792343278");

        ContactService contactService = new ContactService(contactRepository,personRepository);
        contactService.addContact(contactModel);

        Contact contact = newContact.get();
        assertThat(contact).isNotNull();
    }

    @Test
    void shouldReturnUserInfoWithDefaultRole(){
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(user));

        UserDetailsService userDetailsService = new DefaultUserDetailsService(userRepository);
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        assertThat(userDetails).extracting(
                UserDetails::getPassword,
                UserDetails::getUsername
        ).contains(
                user.getPassword(),
                user.getEmail()
        );

        List<String> list = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        assertTrue(list.contains("ROLE_USER"));

    }


    @Test
    void getListOfContactOfPerson(){
      when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        PersonService personService = new PersonService(personRepository, userRepository);
        Set<Contact> contacts = personService.getContactOfPerson(person.getId());
        List<String> list = contacts.stream().map(Contact::getPhoneNumber).collect(Collectors.toList());
        assertThat(list).containsOnly("792343278");
    }

    @Test
    void shouldAddPerson(){
        AtomicReference<Person> personAtomicReference = new AtomicReference<>();
        when(userRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
        when(personRepository.save(any())).thenAnswer(
                inv -> {
                    Person person = inv.getArgument(0);
                    personAtomicReference.set(person);
                    return person;
                }
        );
        PersonService personService = new PersonService(personRepository, userRepository);
        PersonModel personModel = new PersonModel();
        personModel.setFirstName("Dawid");
        personModel.setLastName("Witaszek");
        personModel.setUserEmail("admin");

        personService.addPerson(personModel);

        Person addedPerson = personAtomicReference.get();
        assertThat(addedPerson).isNotNull().extracting("firstName","lastName")
                .contains("Dawid", "Witaszek");

        User userFromAddedPerson = person.getUser();

        assertThat(userFromAddedPerson).isEqualTo(user);
    }




}
