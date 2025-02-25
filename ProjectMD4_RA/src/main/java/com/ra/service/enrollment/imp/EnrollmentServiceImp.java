package com.ra.service.enrollment.imp;

import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.enrollment.EnrollmentResponseDTO;
import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;
import com.ra.model.entity.*;
import com.ra.repository.*;
import com.ra.service.enrollment.EnrollmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImp implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDetailRepository enrollmentDetailRepository;
    private final CourseCartRepository courseCartRepository;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    public EnrollmentServiceImp(EnrollmentRepository enrollmentRepository,
                                EnrollmentDetailRepository enrollmentDetailRepository,
                                CourseCartRepository courseCartRepository,
                                UserRepository userRepository,
                                PaymentRepository paymentRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentDetailRepository = enrollmentDetailRepository;
        this.courseCartRepository = courseCartRepository;
        this.userRepository = userRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public PaymentResponseDTO checkoutCart(PaymentRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<CourseCart> cartItems = courseCartRepository.findByUserUserId(requestDTO.getUserId());
        if (cartItems.isEmpty()) {
            throw new IllegalStateException("Cart is empty");
        }

        // Tính tổng giá
        BigDecimal totalPrice = cartItems.stream()
                .map(item -> item.getCourse().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Tạo Enrollment
        Enrollment enrollment = Enrollment.builder()
                .serialNumber(UUID.randomUUID().toString())
                .user(user)
                .totalPrice(totalPrice)
                .status(EnrollmentStatus.WAITING)
                .createdAt(LocalDateTime.now())
                .build();
        enrollment = enrollmentRepository.save(enrollment);

        // Tạo EnrollmentDetail từ CourseCart
        for (CourseCart cartItem : cartItems) {
            EnrollmentDetail enrollmentDetail = EnrollmentDetail.builder()
                    .enrollment(enrollment)
                    .course(cartItem.getCourse())
                    .courseName(cartItem.getCourse().getCourseName())
                    .unitPrice(cartItem.getCourse().getPrice())
                    .quantity(cartItem.getQuantity())
                    .build();
            enrollmentDetailRepository.save(enrollmentDetail);
        }

        // Xóa giỏ hàng sau khi checkout
        courseCartRepository.deleteAll(cartItems);

        // Tạo Payment
        Payment payment = Payment.builder()
                .enrollment(enrollment)
                .user(user)
                .amount(totalPrice)
                .paymentMethod(PaymentMethod.valueOf(requestDTO.getPaymentMethod().toUpperCase())) // Chuyển từ String sang Enum
                .status(PaymentStatus.PAID) // Dùng Enum thay vì String
                .paidAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .build();

        payment = paymentRepository.save(payment);

        return new PaymentResponseDTO(
                payment.getPaymentId(),
                enrollment.getEnrollmentId(),
                user.getUsername(),
                totalPrice,
                payment.getPaymentMethod().name(),  // Chuyển Enum về String
                payment.getStatus().name(),         // Chuyển Enum về String
                payment.getPaidAt(),
                payment.getCreatedAt()
        );

    }

    @Override
    public List<EnrollmentResponseDTO> getUserEnrollments(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserUserId(userId);
        return enrollments.stream()
                .map(e -> new EnrollmentResponseDTO(
                        e.getEnrollmentId(),
                        e.getSerialNumber(),
                        e.getTotalPrice(),
                        e.getStatus().name(),
                        e.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentDetail(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        return new EnrollmentResponseDTO(
                enrollment.getEnrollmentId(),
                enrollment.getSerialNumber(),
                enrollment.getTotalPrice(),
                enrollment.getStatus().name(),
                enrollment.getCreatedAt()
        );
    }

    @Override
    public void cancelEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));

        if (!enrollment.getStatus().equals(EnrollmentStatus.WAITING)) {
            throw new IllegalStateException("Only pending enrollments can be canceled");
        }

        enrollment.setStatus(EnrollmentStatus.CANCELED);
        enrollmentRepository.save(enrollment);
    }
}
