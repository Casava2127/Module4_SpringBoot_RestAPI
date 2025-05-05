package com.ac.repository;

import com.ac.model.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Certificate
public interface CertificateRepository extends JpaRepository<Certificate, Long> {}