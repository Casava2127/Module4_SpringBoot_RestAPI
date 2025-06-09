package com.ac.service;

import com.ac.model.entity.EventRegistration;
import com.ac.model.entity.RegistrationStatus;
import com.ac.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public List<EventRegistration> getAllEventRegistrations() {
        return eventRegistrationRepository.findAll();
    }

    public Optional<EventRegistration> getEventRegistrationById(Long id) {
        return eventRegistrationRepository.findById(id);
    }

    public EventRegistration createEventRegistration(EventRegistration eventRegistration) {
        return eventRegistrationRepository.save(eventRegistration);
    }

    public EventRegistration updateEventRegistration(Long id, EventRegistration details) {
        EventRegistration reg = eventRegistrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
        reg.setStatus(details.getStatus());
        return eventRegistrationRepository.save(reg);
    }

    public void deleteEventRegistration(Long id) {
        eventRegistrationRepository.deleteById(id);
    }

    public EventRegistration updateRegistrationStatus(Long registrationId, String status) {
        Optional<EventRegistration> regOpt = eventRegistrationRepository.findById(registrationId);
        if (regOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy đăng ký với ID: " + registrationId);
        }
        EventRegistration registration = regOpt.get();
        registration.setStatus(RegistrationStatus.valueOf(status));
        return eventRegistrationRepository.save(registration);
    }
}