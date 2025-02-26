package com.ra.repository;

import com.ra.model.entity.EnrollmentDetail;
import com.ra.model.entity.EnrollmentDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, EnrollmentDetailId> {
    // Định nghĩa phương thức theo quy ước Spring Data JPA
    List<EnrollmentDetail> findByEnrollmentEnrollmentId(Long enrollmentId);
}
