package com.example.demo.specification;

import com.example.demo.entity.Reservation;
import com.example.demo.entity.Room;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import java.time.LocalDateTime;
import java.util.List;

public class RoomSpecification {

    public static Specification<Room> betweenDate(LocalDateTime from, LocalDateTime to) {
        return (Specification<Room>) (root, query, criteriaBuilder) -> {
            Join<Room, Reservation> roomReservertionJoin = root.join("reservationSet");
            return criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(roomReservertionJoin.get("fromDate"), from),
                    criteriaBuilder.lessThanOrEqualTo(roomReservertionJoin.get("toDate"), to)
            ).not();
        };
    }

    public static Specification<Room> isType(final List<String> roomTypeList) {
        return (Specification<Room>) (root, query, criteriaBuilder) -> {
            Path<String> roomTypePath = root.get("roomTypeSet").get("name");
            return roomTypePath.in(roomTypeList);
        };
    }

    public static Specification<Room> hasTag(final List<String> roomTag) {
        return (Specification<Room>) (root, query, criteriaBuilder) -> {
            Path<String> roomTypePath = root.join("tagSet").get("name");
            return roomTypePath.in(roomTag);
        };
    }

    public static Specification<Room> hasAreaEqualTo(int area) {
        return (Specification<Room>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("area"), area);
    }

    public static Specification<Room> hasPersonAmountEqualTo(int personAmount) {
        return (Specification<Room>) (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("personAmount"), personAmount);
    }
}

