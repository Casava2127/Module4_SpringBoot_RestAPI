package com.ac.service;

import com.ac.model.dto.AttendanceResponse;
import com.ac.model.entity.*;
import com.ac.repository.AttendanceRepository;
import com.ac.repository.EventRepository;
import com.ac.repository.QRCodeRepository;
import com.ac.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final QRCodeRepository qrCodeRepository;

    // Sinh viên điểm danh qua QR Code
    public AttendanceResponse markAttendance(Long studentId, Long eventId, Long qrId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        QRCode qrCode = qrCodeRepository.findById(qrId)
                .orElseThrow(() -> new RuntimeException("QR Code not found"));

        // Kiểm tra xem sinh viên đã điểm danh chưa
        if (attendanceRepository.findByStudent_UserIdAndEvent_EventId(studentId, eventId).isPresent()) {
            throw new RuntimeException("Student has already marked attendance for this event");
        }

        Attendance attendance = Attendance.builder()
                .student(student)
                .event(event)
                .qrCode(qrCode)
                .status(AttendanceStatus.PENDING)
                .scanTime(LocalDateTime.now())
                .build();

        attendanceRepository.save(attendance);
        return AttendanceResponse.fromEntity(attendance);
    }

    // BTC/Nhà trường phê duyệt hoặc từ chối điểm danh
    public AttendanceResponse updateAttendanceStatus(Long attendanceId, AttendanceStatus status) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));

        attendance.setStatus(status);
        attendanceRepository.save(attendance);
        return AttendanceResponse.fromEntity(attendance);
    }

    // Lấy danh sách điểm danh theo sự kiện
    public List<AttendanceResponse> getAttendancesByEvent(Long eventId) {
        return attendanceRepository.findByEvent_EventId(eventId).stream()
                .map(AttendanceResponse::fromEntity)
                .collect(Collectors.toList());
    }

    // Lấy danh sách điểm danh theo sinh viên
    public List<AttendanceResponse> getAttendancesByStudent(Long studentId) {
        return attendanceRepository.findByStudent_UserId(studentId).stream()
                .map(AttendanceResponse::fromEntity)
                .collect(Collectors.toList());
    }
}
