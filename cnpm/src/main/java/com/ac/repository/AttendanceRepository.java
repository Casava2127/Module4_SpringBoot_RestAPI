package com.ac.repository;

import com.ac.model.entity.Attendance;
import com.ac.model.entity.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByEvent_EventId(Long eventId);
    List<Attendance> findByStudent_UserId(Long studentId);
    List<Attendance> findByStatus(AttendanceStatus status);
    Optional<Attendance> findByStudent_UserIdAndEvent_EventId(Long studentId, Long eventId);
    int countByStatus(AttendanceStatus status);
}
