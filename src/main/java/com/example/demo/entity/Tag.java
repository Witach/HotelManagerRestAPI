package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
public class Tag {
    @Id
    String name;

    @ManyToMany(mappedBy = "tagSet")
    Set<Room> roomSet;
}
