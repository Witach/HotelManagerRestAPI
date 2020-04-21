package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "room")
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    Long id;

    @Column(name = "room_number")
    @NotBlank
    @Size(min = 1, max = 5)
    String roomNumber;

    @Column(name = "area")
    @Min(20)
    @Max(100)
    @NotBlank
    Integer area;

    @Column(name = "person_amount")
    @Min(1)
    @Max(5)
    @NotBlank
    Integer personAmount;

    @NotBlank
    @Min(24)
    @NotBlank
    String description;

    @Column(name = "room_price")
    @Min(1)
    @NotBlank
    Double price;

    @ManyToMany(mappedBy = "roomSet")
    Set<Reservation> reservationSet = new HashSet<>();

    @ManyToMany
    @BatchSize(size = 10)
    @JsonBackReference
    @JoinTable(
            joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_type_id", referencedColumnName = "room_type_id")}
    )
    Set<RoomType> roomTypeSet = new HashSet<>();

    @ManyToMany
    @BatchSize(size = 10)
    @JsonBackReference
    @JoinTable(
            joinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "room_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}
    )
    Set<Tag> tagSet = new HashSet<>();



}
