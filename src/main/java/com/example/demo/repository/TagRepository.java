package com.example.demo.repository;

import com.example.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(path = "tags")
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Override
    @SecuredAdmin
    <S extends Tag> S save(S entity);

    @Override
    @SecuredAdmin
    void deleteById(Long aLong);

    @Override
    @SecuredAdmin
    void delete(Tag entity);

    @Override
    @SecuredAdmin
    void deleteAll(Iterable<? extends Tag> entities);
}
