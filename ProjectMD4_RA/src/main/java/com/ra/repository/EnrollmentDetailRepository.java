//package com.ra.repository;
//
//import com.ra.model.entity.EnrollmentDetail;
//import com.ra.model.entity.EnrollmentDetailId;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
////@Repository
//public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, EnrollmentDetailId> {
//}
package com.ra.repository;

import com.ra.model.entity.EnrollmentDetail;
import com.ra.model.entity.EnrollmentDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrollmentDetailRepository extends JpaRepository<EnrollmentDetail, EnrollmentDetailId> {
}


