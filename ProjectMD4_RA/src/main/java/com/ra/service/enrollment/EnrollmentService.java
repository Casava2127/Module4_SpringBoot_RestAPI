//package com.ra.service.enrollment;
//
//import com.ra.model.dto.enrollment.EnrollmentRequestDTO;
//import com.ra.model.dto.enrollment.EnrollmentResponseDTO;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface EnrollmentService {
//    List<EnrollmentResponseDTO> findAll();
//    Optional<EnrollmentResponseDTO> findById(Long id);
//    EnrollmentResponseDTO save(EnrollmentRequestDTO dto);
//    EnrollmentResponseDTO update(Long id, EnrollmentRequestDTO dto);
//    boolean delete(Long id);
//}
package com.ra.service.enrollment;


import com.ra.model.dto.enrollment.EnrollmentResponseDTO;
import com.ra.model.dto.enrollmentDetail.EnrollmentDetailResponseDTO;
import com.ra.model.dto.payment.PaymentRequestDTO;
import com.ra.model.dto.payment.PaymentResponseDTO;

import java.util.List;

public interface EnrollmentService {
    PaymentResponseDTO checkoutCart(PaymentRequestDTO requestDTO);
    List<EnrollmentResponseDTO> getUserEnrollments(Long userId);
    void cancelEnrollment(Long enrollmentId);
    List<EnrollmentDetailResponseDTO> getEnrollmentDetail(Long enrollmentId);
}
