package com.example.demo.repository;

import com.example.demo.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@RepositoryRestResource(path = "bills")
@SecuredWithBoth
public interface BillRepository extends JpaRepository<Bill, Long> {

    @Override
    @SecuredAdmin
    void deleteById(Long aLong);

    @Override
    @SecuredAdmin
    void delete(Bill entity);

    @Override
    @SecuredAdmin
    Bill save(Bill entity);
}
