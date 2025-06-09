package com.ac.controller;

import com.ac.model.dto.ApprovalRequest;
import com.ac.model.entity.Event;
import com.ac.model.entity.User;
import com.ac.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public List<Event> getAllEvents() {
        return service.getAllEvents();
    }

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable Long id) {
        return service.getEventById(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return service.createEvent(event);
    }

    @PutMapping("/{id}")
    public Event updateEvent(@PathVariable Long id, @RequestBody Event event) {
        return service.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        service.deleteEvent(id);
    }

    @PutMapping("/{eventId}/approval")
    public ResponseEntity<Event> updateEventApproval(@PathVariable Long eventId,
                                                     @RequestBody ApprovalRequest request) {
        Event updatedEvent = service.updateEventApproval(eventId, request.getStatus());
        return ResponseEntity.ok(updatedEvent);
    }

    @GetMapping("/{eventId}/participants")
    public ResponseEntity<List<User>> getEventParticipants(@PathVariable Long eventId) {
        List<User> participants = service.getEventParticipants(eventId);
        return ResponseEntity.ok(participants);
    }
}