package com.ac.controller;

import com.ac.model.entity.EventPost;
import com.ac.service.EventPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
public class EventPostController {

    @Autowired
    private EventPostService service;

    @GetMapping
    public List<EventPost> getAllEventPosts() {
        return service.getAllEventPosts();
    }

    @GetMapping("/{id}")
    public Optional<EventPost> getEventPostById(@PathVariable Long id) {
        return service.getEventPostById(id);
    }

    @PostMapping
    public EventPost createEventPost(@RequestBody EventPost post) {
        return service.createEventPost(post);
    }

    @PutMapping("/{id}")
    public EventPost updateEventPost(@PathVariable Long id, @RequestBody EventPost post) {
        return service.updateEventPost(id, post);
    }

    @DeleteMapping("/{id}")
    public void deleteEventPost(@PathVariable Long id) {
        service.deleteEventPost(id);
    }
}