package com.example.back_end.service;

import com.example.back_end.dto.EventDTO;
import com.example.back_end.model.Event;
import com.example.back_end.repository.EventRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class EventService {

    @Autowired
    private EventRepo eventRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ImageService imageService;

    public Page<EventDTO> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable).map(event -> modelMapper.map(event, EventDTO.class));
    }

    public EventDTO getEventById(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        return modelMapper.map(event, EventDTO.class);
    }

    public EventDTO createEvent(EventDTO eventDTO, MultipartFile image) throws IOException {
        String imageUrl = imageService.uploadImage(image, "events");
        Event event = modelMapper.map(eventDTO, Event.class);
        event.setEventImage(imageUrl);
        Event savedEvent = eventRepository.save(event);
        return modelMapper.map(savedEvent, EventDTO.class);
    }

    public Page<EventDTO> getEventsByUserId(String userId, Pageable pageable) {
        return eventRepository.findByUserId(userId, pageable).map(event -> modelMapper.map(event, EventDTO.class));
    }

    public EventDTO updateEvent(EventDTO eventDTO, MultipartFile image) throws IOException {
        Event event = eventRepository.findById(eventDTO.getEventId()).orElseThrow(() -> new RuntimeException("Event not found"));

        if(image != null) {
            if(imageService.deleteImageFromS3(event.getEventImage()) == "ok") {
                //String newImageUrl = imageService.uploadImage(image, "events");
                eventDTO.setEventImage(imageService.uploadImage(image, "events"));
            } else {
                throw new RuntimeException("Image Can't Delete.");
            }
        } else {
            eventDTO.setEventImage(event.getEventImage());
        }

        //System.out.println(eventDTO);
        Event updatedEvent = eventRepository.save(modelMapper.map(eventDTO, Event.class));
        return modelMapper.map(updatedEvent, EventDTO.class);
    }

    public void deleteEvent(Integer eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        imageService.deleteImageFromS3(event.getEventImage());
        eventRepository.delete(event);
    }



    public Page<EventDTO> getPendingEvents(Pageable pageable) {
        return eventRepository.findByEventStatus("PENDING", pageable)
                .map(event -> modelMapper.map(event, EventDTO.class));
    }

    public EventDTO approveEvent(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setEventStatus("APPROVED");
        Event updatedEvent = eventRepository.save(event);
        return modelMapper.map(updatedEvent, EventDTO.class);
    }

    public EventDTO rejectEvent(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        event.setEventStatus("REJECTED");
        Event updatedEvent = eventRepository.save(event);
        return modelMapper.map(updatedEvent, EventDTO.class);
    }

}

