package com.example.demo.repository;

import com.example.demo.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;
@Repository
@RepositoryRestResource(path = "contacts")
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
