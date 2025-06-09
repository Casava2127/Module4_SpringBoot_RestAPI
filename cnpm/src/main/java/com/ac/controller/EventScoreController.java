package com.ac.controller;



import com.ac.model.entity.EventScore;
import com.ac.service.EventScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/scores")
public class EventScoreController {

    @Autowired
    private EventScoreService service;

    @GetMapping
    public List<EventScore> getAllEventScores() {
        return service.getAllEventScores();
    }

    @GetMapping("/{id}")
    public Optional<EventScore> getEventScoreById(@PathVariable Long id) {
        return service.getEventScoreById(id);
    }

    @PostMapping
    public EventScore createEventScore(@RequestBody EventScore score) {
        return service.createEventScore(score);
    }

    @PutMapping("/{id}")
    public EventScore updateEventScore(@PathVariable Long id, @RequestBody EventScore score) {
        return service.updateEventScore(id, score);
    }

    @DeleteMapping("/{id}")
    public void deleteEventScore(@PathVariable Long id) {
        service.deleteEventScore(id);
    }
}