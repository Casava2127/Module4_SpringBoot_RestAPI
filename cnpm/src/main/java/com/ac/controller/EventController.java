//package com.ac.controller;
//
//import com.ac.model.entity.Event;
//import com.ac.service.EventService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/events")
//@RequiredArgsConstructor
//public class EventController {
//    private final EventService eventService;
//
//    @GetMapping
//    public ResponseEntity<List<Event>> getAllEvents() {
//        return ResponseEntity.ok(eventService.getAllEvents());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
//        return eventService.getEventById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
//        return ResponseEntity.ok(eventService.createEvent(event));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event event) {
//        return ResponseEntity.ok(eventService.updateEvent(id, event));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
//        eventService.deleteEvent(id);
//        return ResponseEntity.noContent().build();
//    }
//}
