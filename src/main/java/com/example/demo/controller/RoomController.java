package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.exceptions.ApiError;
import com.example.demo.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeParseException;

import static com.example.demo.config.Messages.makeMessageForController;

@Slf4j
@RestController
public class RoomController {


    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/rooms")
    public ResponseEntity<Page<Room>> getListOfRooms(Pageable pageable, @RequestParam MultiValueMap<String, String> params) {
        log.info(makeMessageForController("GET [/rooms]"));
        Specification<Room> specification = roomService.getSpecififcationFromParams(params);
        Page<Room> roomPage = roomService.getPageOfRoomWithSearching(pageable, specification);
        return ResponseEntity.ok(roomPage);
    }

    @ExceptionHandler({DateTimeParseException.class})
    public ResponseEntity<ApiError> handleDateParseException(DateTimeParseException e){
        ApiError apiError =  buildApiError(e.getMessage(), "Date should be passed as YY-MM-DD string");
        return ResponseEntity.badRequest()
                .body(apiError);
    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<ApiError> handleNumberFormatException(NumberFormatException e){
        ApiError apiError = buildApiError(e.getMessage(), "Filed for number cannot contain characters");
        return ResponseEntity.badRequest()
                .body(apiError);
    }

    private ApiError buildApiError(String excMessage, String debugMessage){
        return ApiError.builder()
                .debugMessage(debugMessage)
                .message(excMessage)
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

}
