package com.ra.repository;

import com.ra.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByOrderByNameAsc();
    List<Employee> findByNameContainingOrAddressContainingOrEmailContainingOrPhoneContaining(
            String name, String address, String email, String phone);
}