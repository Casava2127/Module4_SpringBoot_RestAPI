package com.ra.repository;

import com.ra.model.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
}
