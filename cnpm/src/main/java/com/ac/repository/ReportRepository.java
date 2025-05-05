package com.ac.repository;


import com.ac.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Repository for Report
public interface ReportRepository extends JpaRepository<Report, Long> {}
