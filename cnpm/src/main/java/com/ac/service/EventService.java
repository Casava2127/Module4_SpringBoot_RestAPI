package com.ac.service;

import com.ac.model.entity.*;
import com.ac.repository.AttendanceRepository;
import com.ac.repository.EventRegistrationRepository;
import com.ac.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        event.setEventName(eventDetails.getEventName());
        event.setDescription(eventDetails.getDescription());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setRegistrationDeadline(eventDetails.getRegistrationDeadline());
        event.setStatus(eventDetails.getStatus());
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Event updateEventApproval(Long eventId, String approvalStatus) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        if (!"APPROVED".equalsIgnoreCase(approvalStatus) && !"REJECTED".equalsIgnoreCase(approvalStatus)) {
            throw new IllegalArgumentException("Invalid status. Allowed values: APPROVED or REJECTED");
        }
        event.setStatus(EventStatus.valueOf(approvalStatus.toUpperCase()));
        return eventRepository.save(event);
    }

    public List<User> getEventParticipants(Long eventId) {
        List<EventRegistration> registrations = eventRegistrationRepository.findByEvent_EventId(eventId);
        List<Attendance> attendances = attendanceRepository.findByEvent_EventId(eventId);
        Set<User> participants = new HashSet<>();
        for (EventRegistration reg : registrations) {
            if ("APPROVED".equalsIgnoreCase(String.valueOf(reg.getStatus()))) {
                participants.add(reg.getStudent());
            }
        }
        for (Attendance att : attendances) {
            if (att.getStatus() == AttendanceStatus.APPROVED) {
                participants.add(att.getStudent());
            }
        }
        return new ArrayList<>(participants);
    }
}