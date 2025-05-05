package com.ac.repository;


import com.ac.model.entity.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for QRCode
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {}