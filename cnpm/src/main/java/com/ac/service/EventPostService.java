package com.ac.service;



import com.ac.model.entity.EventPost;
import com.ac.repository.EventPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventPostService {

    @Autowired
    private EventPostRepository eventPostRepository;

    public List<EventPost> getAllEventPosts() {
        return eventPostRepository.findAll();
    }

    public Optional<EventPost> getEventPostById(Long id) {
        return eventPostRepository.findById(id);
    }

    public EventPost createEventPost(EventPost eventPost) {
        return eventPostRepository.save(eventPost);
    }

    public EventPost updateEventPost(Long id, EventPost details) {
        EventPost post = eventPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventPost not found with id: " + id));
        post.setContent(details.getContent());
        return eventPostRepository.save(post);
    }

    public void deleteEventPost(Long id) {
        eventPostRepository.deleteById(id);
    }
}