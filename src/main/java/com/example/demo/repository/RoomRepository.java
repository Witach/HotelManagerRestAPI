package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

@RepositoryRestResource(path = "rooms")
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    @Override
    @RestResource(exported = false)
    List<Room> findAll();

    @Override
    @RestResource(exported = false)
    List<Room> findAll(Sort sort);

    @Override
    @RestResource(exported = false)
    Page<Room> findAll(Pageable pageable);
}
