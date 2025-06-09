package com.ac.service;

import com.ac.model.dto.StatisticsReportResponse;
import com.ac.model.entity.AttendanceStatus;
import com.ac.model.entity.Certificate;
import com.ac.model.entity.EventScore;
import com.ac.model.entity.Report;
import com.ac.repository.AttendanceRepository;
import com.ac.repository.CertificateRepository;
import com.ac.repository.EventScoreRepository;
import com.ac.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportService {


    @Autowired
    private ReportRepository reportRepository;
    private  AttendanceRepository attendanceRepository;
    private  CertificateRepository certificateRepository;
    private  EventScoreRepository eventScoreRepository;




    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    public Report updateReport(Long id, Report details) {
        Report rep = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found with id: " + id));
        rep.setReportContent(details.getReportContent());
        return reportRepository.save(rep);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }

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