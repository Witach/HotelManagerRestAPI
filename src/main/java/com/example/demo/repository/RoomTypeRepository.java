package com.example.demo.repository;

import com.example.demo.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


@Repository
@RepositoryRestResource(path = "types")
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
}
