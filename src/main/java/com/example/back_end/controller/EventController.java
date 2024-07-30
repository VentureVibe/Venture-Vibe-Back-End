package com.example.back_end.controller;

import com.example.back_end.dto.EventDTO;
import com.example.back_end.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvents(
            @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        //List<EventDTO> events = eventService.getAllEvents();
        Page<EventDTO> eventsPage = eventService.getAllEvents(pageable);
        return ResponseEntity.ok(eventsPage);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable("eventId") Integer eventId) {
        EventDTO event = eventService.getEventById(eventId);
        return ResponseEntity.ok(event);
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<EventDTO> createEvent(
            @RequestPart("event") String eventDTOJson,
            @RequestPart("image") MultipartFile image) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        EventDTO event = mapper.readValue(eventDTOJson, EventDTO.class);
        EventDTO createdEvent = eventService.createEvent(event, image);
        return ResponseEntity.ok(createdEvent);
    }

    /*@PutMapping("/{eventId}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("eventId") Integer eventId, @RequestBody EventDTO eventDTO) {
        eventDTO.setEventId(eventId);
        EventDTO updatedEvent = eventService.updateEvent(eventDTO);
        return ResponseEntity.ok(updatedEvent);
    }*/
    @PutMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<EventDTO> updateEvent(
            @RequestParam("event") String eventJson,
            @RequestParam(value = "image", required = false) MultipartFile image) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EventDTO eventDTO = objectMapper.readValue(eventJson, EventDTO.class);
            EventDTO updatedEvent = eventService.updateEvent(eventDTO, image);
            return ResponseEntity.ok(updatedEvent);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("eventId") Integer eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
