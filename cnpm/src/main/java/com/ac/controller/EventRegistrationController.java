package com.ac.controller;

import com.ac.model.dto.UpdateRegistrationStatusRequest;
import com.ac.model.entity.EventRegistration;
import com.ac.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registrations")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService service;

    @GetMapping
    public List<EventRegistration> getAllEventRegistrations() {
        return service.getAllEventRegistrations();
    }

    @GetMapping("/{id}")
    public Optional<EventRegistration> getEventRegistrationById(@PathVariable Long id) {
        return service.getEventRegistrationById(id);
    }

    @PostMapping
    public EventRegistration createEventRegistration(@RequestBody EventRegistration reg) {
        return service.createEventRegistration(reg);
    }

    @PutMapping("/{id}")
    public EventRegistration updateEventRegistration(@PathVariable Long id, @RequestBody EventRegistration reg) {
        return service.updateEventRegistration(id, reg);
    }

    @DeleteMapping("/{id}")
    public void deleteEventRegistration(@PathVariable Long id) {
        service.deleteEventRegistration(id);
    }

    @PutMapping("/{id}/approval")
    public ResponseEntity<?> updateRegistrationStatus(@PathVariable Long id,
                                                      @RequestBody UpdateRegistrationStatusRequest request) {
        try {
            EventRegistration updatedRegistration = service.updateRegistrationStatus(id, request.getStatus());
            return ResponseEntity.ok(updatedRegistration);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}