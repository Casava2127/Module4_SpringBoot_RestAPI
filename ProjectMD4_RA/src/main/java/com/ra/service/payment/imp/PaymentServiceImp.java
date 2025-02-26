package com.ra.service.payment.imp;

import com.ra.exception.ResourceNotFoundException;
import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;
import com.ra.model.entity.*;
import com.ra.repository.CourseCartRepository;
import com.ra.repository.EnrollmentRepository;
import com.ra.repository.PaymentRepository;
import com.ra.repository.UserRepository;
import com.ra.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImp implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    private CourseCartRepository courseCartRepository;

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
        payment.setPaymentMethod(PaymentMethod.valueOf(paymentDTO.getPaymentMethod()));
        payment.setStatus(PaymentStatus.valueOf(paymentDTO.getStatus()));
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



//    @Override
//    public PaymentResponseDTO checkoutCart(PaymentRequestDTO paymentRequestDTO) {
//        // Lấy thông tin người dùng
//        User user = userRepository.findById(paymentRequestDTO.getUserId())
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//
//        // Lấy danh sách khóa học trong giỏ hàng
//        List<CourseCart> cartItems = courseCartRepository.findByUserUserId(user.getUserId());
//        if (cartItems.isEmpty()) {
//            throw new IllegalStateException("Cart is empty");
//        }
//
//        // Tạo danh sách đăng ký khóa học (Enrollment)
//        List<Enrollment> enrollments = new ArrayList<>();
//        for (CourseCart cartItem : cartItems) {
//            EnrollmentDetailId enrollmentDetailId = new EnrollmentDetailId(enrollment.getEnrollmentId(), cartItem.getCourse().getCourseId());
//
//            EnrollmentDetail detail = EnrollmentDetail.builder()
//                    .id(enrollmentDetailId)
//                    .enrollment(enrollment)
//                    .course(cartItem.getCourse())
//                    .courseName(cartItem.getCourse().getTitle())
//                    .unitPrice(cartItem.getCourse().getPrice())
//                    .quantity(1) // Giả sử mặc định là 1
//                    .build();
//
//            enrollment.getEnrollmentDetails().add(detail);
//        }
//
//        enrollmentRepository.saveAll(enrollments);
//
//        // Tính tổng tiền thanh toán
//        BigDecimal totalAmount = enrollments.stream()
//                .map(e -> e.getCourse().getPrice())
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        // Tạo giao dịch thanh toán
//        Payment payment = Payment.builder()
//                .user(user)
//                .amount(totalAmount)
//                .paymentMethod(PaymentMethod.valueOf(paymentRequestDTO.getPaymentMethod()))
//                .status(PaymentStatus.PENDING) // Trạng thái thanh toán ban đầu
//                .paidAt(LocalDateTime.now())
//                .createdAt(LocalDateTime.now())
//                .build();
//        payment = paymentRepository.save(payment);
//
//        // Xóa giỏ hàng sau khi thanh toán thành công
//        courseCartRepository.deleteByUserUserId(user.getUserId());
//
//        // Trả về thông tin thanh toán
//        return PaymentResponseDTO.builder()
//                .paymentId(payment.getPaymentId())
//                .userName(user.getUsername())
//                .amount(payment.getAmount())
//                .paymentMethod(payment.getPaymentMethod().name())
//                .status(payment.getStatus().name())
//                .paidAt(payment.getPaidAt())
//                .createdAt(payment.getCreatedAt())
//                .build();
//    }
@Override
public PaymentResponseDTO checkoutCart(PaymentRequestDTO paymentRequestDTO) {
    // 1️⃣ Lấy thông tin người dùng
    User user = userRepository.findById(paymentRequestDTO.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

    // 2️⃣ Lấy danh sách khóa học trong giỏ hàng
    List<CourseCart> cartItems = courseCartRepository.findByUserUserId(user.getUserId());
    if (cartItems.isEmpty()) {
        throw new IllegalStateException("Cart is empty");
    }

    // 3️⃣ Tạo Enrollment (Đăng ký khóa học)
    Enrollment enrollment = Enrollment.builder()
            .serialNumber("ENR-" + System.currentTimeMillis()) // Mã đăng ký duy nhất
            .user(user)
            .totalPrice(BigDecimal.ZERO) // Sẽ cập nhật sau
            .status(EnrollmentStatus.WAITING)
            .createdAt(LocalDateTime.now())
            .build();

    // 🔹 Lưu enrollment trước để có ID
    Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

    // 4️⃣ Tạo EnrollmentDetail (Chi tiết đăng ký khóa học)
    List<EnrollmentDetail> enrollmentDetails = new ArrayList<>();
    for (CourseCart cartItem : cartItems) {
        EnrollmentDetailId enrollmentDetailId = new EnrollmentDetailId(
                savedEnrollment.getEnrollmentId(), cartItem.getCourse().getCourseId());

        EnrollmentDetail detail = EnrollmentDetail.builder()
                .id(enrollmentDetailId)
                .enrollment(savedEnrollment)
                .course(cartItem.getCourse())
                .courseName(cartItem.getCourse().getCourseName())
                .unitPrice(cartItem.getCourse().getPrice())
                .quantity(1)
                .build();

        enrollmentDetails.add(detail);
    }

    // 🔹 Lưu danh sách EnrollmentDetail
    savedEnrollment.setEnrollmentDetails(enrollmentDetails);
    enrollmentRepository.save(savedEnrollment);

    // 5️⃣ Tính tổng tiền thanh toán
    BigDecimal totalAmount = enrollmentDetails.stream()
            .map(detail -> detail.getUnitPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // 6️⃣ Tạo giao dịch thanh toán
    Payment payment = Payment.builder()
            .user(user)
            .amount(totalAmount)
            .paymentMethod(PaymentMethod.valueOf(paymentRequestDTO.getPaymentMethod()))
            .status(PaymentStatus.PENDING) // Trạng thái thanh toán ban đầu
            .paidAt(LocalDateTime.now())
            .createdAt(LocalDateTime.now())
            .build();

    // 🔹 Lưu Payment
    payment = paymentRepository.save(payment);

    // 7️⃣ Xóa giỏ hàng sau khi thanh toán thành công
    courseCartRepository.deleteByUserUserId(user.getUserId());

    // 8️⃣ Trả về thông tin thanh toán
    return PaymentResponseDTO.builder()
            .paymentId(payment.getPaymentId())
            .userName(user.getUsername())
            .amount(payment.getAmount())
            .paymentMethod(payment.getPaymentMethod().name())
            .status(payment.getStatus().name())
            .paidAt(payment.getPaidAt())
            .createdAt(payment.getCreatedAt())
            .build();
}



    private PaymentResponseDTO convertToDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .paymentId(payment.getPaymentId())
                .enrollmentId(payment.getEnrollment().getEnrollmentId())
                .userName(payment.getUser().getUsername())
                .amount(payment.getAmount())
                .paymentMethod(String.valueOf(payment.getPaymentMethod()))
                .status(String.valueOf(payment.getStatus()))
                .paidAt(payment.getPaidAt())
                .createdAt(payment.getCreatedAt())
                .build();
    }

    private Payment convertToEntity(PaymentRequestDTO paymentDTO) {
        if (paymentDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        if (paymentDTO.getEnrollmentId() == null) {
            throw new IllegalArgumentException("Enrollment ID must not be null");
        }

        Enrollment enrollment = enrollmentRepository.findById(paymentDTO.getEnrollmentId())
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        User user = userRepository.findById(paymentDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return Payment.builder()
                .enrollment(enrollment)
                .user(user)
                .amount(paymentDTO.getAmount())
                .paymentMethod(PaymentMethod.valueOf(paymentDTO.getPaymentMethod()))
                .status(PaymentStatus.valueOf(paymentDTO.getStatus()))
                .paidAt(paymentDTO.getPaidAt())
                .createdAt(java.time.LocalDateTime.now())
                .build();
    }

}
