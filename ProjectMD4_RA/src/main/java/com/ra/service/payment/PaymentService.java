package com.ra.service.payment;

import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<PaymentResponseDTO> findAll();
    Optional<PaymentResponseDTO> findById(Long id);
    PaymentResponseDTO save(PaymentRequestDTO paymentDTO);
    PaymentResponseDTO update(Long id, PaymentRequestDTO paymentDTO);
    boolean delete(Long id);
}
