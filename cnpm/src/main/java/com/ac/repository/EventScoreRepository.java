package com.ac.repository;

import com.ac.model.entity.EventScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventScoreRepository extends JpaRepository<EventScore, Long> {
    Optional<EventScore> findByEvent_EventId(Long eventId);
}
