package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class RoomController {


    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService ) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<Page<Room>> getListOfRooms(Pageable pageable, @RequestParam Map<String, List<String>> params){
        Specification<Room> specification = roomService.getSpecififcationFromParams(params);
        Page<Room> roomPage = roomService.getPageOfRoomWithSearching(pageable,specification);
        return ResponseEntity.ok(roomPage);
    }
}
