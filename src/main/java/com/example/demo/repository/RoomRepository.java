package com.example.demo.repository;

import com.example.demo.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "rooms")
public interface RoomRepository extends JpaRepository<Room, Long>, JpaSpecificationExecutor<Room> {
    @Override
    @SecuredAdmin
    List<Room> findAll();

    @Override
    @SecuredAdmin
    List<Room> findAll(Sort sort);

    @Override
    @SecuredAdmin
    Page<Room> findAll(Pageable pageable);

    @Override
    @SecuredAdmin
    Room save(Room room);

    @Override
    @SecuredAdmin
    void deleteInBatch(Iterable<Room> entities);

    @Override
    @SecuredAdmin
    void deleteById(Long aLong);

    @Override
    @SecuredAdmin
    void delete(Room entity);

    @Override
    @SecuredAdmin
    void deleteAll(Iterable<? extends Room> entities);
}
