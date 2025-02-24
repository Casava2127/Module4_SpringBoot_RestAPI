package com.ra.service.payment.imp;

import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;
import com.ra.model.entity.Enrollment;
import com.ra.model.entity.Payment;
import com.ra.model.entity.User;
import com.ra.repository.EnrollmentRepository;
import com.ra.repository.PaymentRepository;
import com.ra.repository.UserRepository;
import com.ra.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PaymentResponseDTO> findAll() {
        return paymentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PaymentResponseDTO> findById(Long id) {
        return paymentRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public PaymentResponseDTO save(PaymentRequestDTO paymentDTO) {
        Payment payment = convertToEntity(paymentDTO);
        Payment savedPayment = paymentRepository.save(payment);
        return convertToDTO(savedPayment);
    }

    @Override
    public PaymentResponseDTO update(Long id, PaymentRequestDTO paymentDTO) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (!optionalPayment.isPresent()) return null;

        Payment payment = optionalPayment.get();
        payment.setAmount(paymentDTO.getAmount());
        payment.setPaymentMethod(paymentDTO.getPaymentMethod());
        payment.setStatus(paymentDTO.getStatus());
        payment.setPaidAt(paymentDTO.getPaidAt());

        Payment updatedPayment = paymentRepository.save(payment);
        return convertToDTO(updatedPayment);
    }

    @Override
    public boolean delete(Long id) {
        if (!paymentRepository.existsById(id)) return false;
        paymentRepository.deleteById(id);
        return true;
    }

    private PaymentResponseDTO convertToDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .enrollmentId(payment.getEnrollment().getEnrollmentId())
                .userName(payment.getUser().getUsername())
                .amount(payment.getAmount())
                .paymentMethod(payment.getPaymentMethod())
                .status(payment.getStatus())
                .paidAt(payment.getPaidAt())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    private Payment convertToEntity(PaymentRequestDTO paymentDTO) {
        Enrollment enrollment = enrollmentRepository.findById(paymentDTO.getEnrollmentId()).orElse(null);
        User user = userRepository.findById(paymentDTO.getUserId()).orElse(null);

        return Payment.builder()
                .enrollment(enrollment)
                .user(user)
                .amount(paymentDTO.getAmount())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .status(paymentDTO.getStatus())
                .paidAt(paymentDTO.getPaidAt())
                .createdAt(java.time.LocalDateTime.now())
                .build();
    }
}
