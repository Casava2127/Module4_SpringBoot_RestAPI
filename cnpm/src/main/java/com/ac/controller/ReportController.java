package com.ac.controller;


import com.ac.model.dto.StatisticsReportResponse;
import com.ac.model.entity.Report;

import com.ac.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    @Autowired
    private final ReportService reportService;
    
    
    @GetMapping("/statistics")
    public ResponseEntity<StatisticsReportResponse> getCommunityActivityStatistics() {
        StatisticsReportResponse stats = reportService.getCommunityActivityStatistics();
        return ResponseEntity.ok(stats);
    }

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public Optional<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id);
    }

    @PostMapping
    public Report createReport(@RequestBody Report rep) {
        return reportService.createReport(rep);
    }

    @PutMapping("/{id}")
    public Report updateReport(@PathVariable Long id, @RequestBody Report rep) {
        return reportService.updateReport(id, rep);
    }

    @DeleteMapping("/{id}")
    public void deleteReport(@PathVariable Long id) {
        reportService.deleteReport(id);
    }
}