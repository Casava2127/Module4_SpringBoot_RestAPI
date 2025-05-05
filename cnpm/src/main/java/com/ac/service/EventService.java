package com.ac.service;

import com.ac.model.entity.Event;
import com.ac.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    // Lấy danh sách tất cả sự kiện
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    // Lấy sự kiện theo ID
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    // Tạo mới sự kiện
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    // Cập nhật sự kiện theo ID
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sự kiện với id: " + id));
        event.setEventName(eventDetails.getEventName());
        event.setDescription(eventDetails.getDescription());
        event.setStartDate(eventDetails.getStartDate());
        event.setEndDate(eventDetails.getEndDate());
        event.setRegistrationDeadline(eventDetails.getRegistrationDeadline());
        event.setStatus(eventDetails.getStatus());
        // Cập nhật các trường khác nếu cần
        return eventRepository.save(event);
    }

    // Xóa sự kiện theo ID
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
