package com.ac.Note.service;

import com.ac.model.dto.StatisticsReportResponse;
import com.ac.model.entity.Attendance;
import com.ac.model.entity.AttendanceStatus;
import com.ac.model.entity.Certificate;
import com.ac.model.entity.EventScore;
import com.ac.repository.AttendanceRepository;
import com.ac.repository.CertificateRepository;
import com.ac.repository.EventScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final AttendanceRepository attendanceRepository;
    private final CertificateRepository certificateRepository;
    private final EventScoreRepository eventScoreRepository;

    public StatisticsReportResponse getCommunityActivityStatistics() {
        // Đếm điểm danh theo trạng thái
        int approvedAttendances = attendanceRepository.countByStatus(AttendanceStatus.APPROVED);
        int rejectedAttendances = attendanceRepository.countByStatus(AttendanceStatus.REJECTED);

        // Tính tổng điểm của mỗi sinh viên dựa trên chứng chỉ (Certificate)
        // Giả sử: Mỗi chứng chỉ của một sự kiện mang lại điểm = maxScore (từ EventScore)
        Map<Long, Integer> totalScorePerStudent = new HashMap<>();
        Map<Long, Integer> totalScorePerEvent = new HashMap<>();

        List<Certificate> certificates = certificateRepository.findAll();
        for (Certificate cert : certificates) {
            Long studentId = cert.getStudent().getUserId();  // Nếu User entity đã đổi trường thành "id"
            Long eventId = cert.getEvent().getEventId();   // Giả sử Event có trường eventId

            // Lấy điểm của sự kiện từ EventScore
            EventScore eventScore = eventScoreRepository.findByEvent_EventId(eventId)
                    .orElse(null);
            if (eventScore != null) {
                int score = eventScore.getMaxScore();
                // Cộng dồn điểm theo sinh viên
                totalScorePerStudent.put(studentId, totalScorePerStudent.getOrDefault(studentId, 0) + score);
                // Cộng dồn điểm theo sự kiện
                totalScorePerEvent.put(eventId, totalScorePerEvent.getOrDefault(eventId, 0) + score);
            }
        }

        return StatisticsReportResponse.builder()
                .approvedAttendances(approvedAttendances)
                .rejectedAttendances(rejectedAttendances)
                .totalScorePerStudent(totalScorePerStudent)
                .totalScorePerEvent(totalScorePerEvent)
                .build();
    }
}
