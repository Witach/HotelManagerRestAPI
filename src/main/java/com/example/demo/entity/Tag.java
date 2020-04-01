package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    Long id;

    @Column(unique = true)
    String name;

    @ManyToMany(mappedBy = "tagSet")
    Set<Room> roomSet;
}