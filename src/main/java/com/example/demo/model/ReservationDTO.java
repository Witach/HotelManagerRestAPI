package com.example.demo.model;

import com.example.demo.entity.Bill;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.Room;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReservationDTO {
    Long id;
    List<String> roomSet;
    Bill bill;
    LocalDate fromDate;
    LocalDate toDate;

    public static ReservationDTO makeFromEntity(Reservation reservation){
        List<String> idsList = reservation.getRoomSet().stream()
                .mapToLong(Room::getId)
                .mapToObj(Long::toString)
                .collect(Collectors.toList());

        return ReservationDTO.builder()
                .bill(reservation.getBill())
                .fromDate(reservation.getFromDate())
                .toDate(reservation.getToDate())
                .id(reservation.getId())
                .roomSet(idsList)
                .build();
    }
}
