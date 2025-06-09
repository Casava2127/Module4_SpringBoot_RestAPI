package com.ac.service;

import com.ac.model.entity.EventScore;
import com.ac.repository.EventScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventScoreService {

    @Autowired
    private EventScoreRepository eventScoreRepository;

    public List<EventScore> getAllEventScores() {
        return eventScoreRepository.findAll();
    }

    public Optional<EventScore> getEventScoreById(Long id) {
        return eventScoreRepository.findById(id);
    }

    public EventScore createEventScore(EventScore eventScore) {
        return eventScoreRepository.save(eventScore);
    }

    public EventScore updateEventScore(Long id, EventScore details) {
        EventScore score = eventScoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventScore not found with id: " + id));
        score.setMinScore(details.getMinScore());
        score.setMaxScore(details.getMaxScore());
        return eventScoreRepository.save(score);
    }

    public void deleteEventScore(Long id) {
        eventScoreRepository.deleteById(id);
    }
}