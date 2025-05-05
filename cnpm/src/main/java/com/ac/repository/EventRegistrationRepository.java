package com.ac.repository;

import com.ac.model.entity.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Repository for EventRegistration
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    Optional<EventRegistration> findById(Long id);

    List<EventRegistration> findByEvent_EventId(Long eventId);
    int countByStatus(String status);
}
