package com.example.demo.repository;

import com.example.demo.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@SecuredAdmin
@RepositoryRestResource
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
