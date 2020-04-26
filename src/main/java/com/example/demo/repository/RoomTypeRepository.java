package com.example.demo.repository;

import com.example.demo.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(path = "types")
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    @Override
    @SecuredAdmin
    <S extends RoomType> S save(S entity);

    @Override
    @SecuredAdmin
    void deleteById(Long aLong);

    @Override
    @SecuredAdmin
    void delete(RoomType entity);

    @Override
    @SecuredAdmin
    void deleteAll(Iterable<? extends RoomType> entities);
}
