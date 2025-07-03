package com.ac.Note.controller;

import com.ac.model.dto.StatisticsReportResponse;
import com.ac.Note.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    // Endpoint báo cáo thống kê điểm hoạt động cộng đồng
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsReportResponse> getCommunityActivityStatistics() {
        StatisticsReportResponse stats = reportService.getCommunityActivityStatistics();
        return ResponseEntity.ok(stats);
    }
}
