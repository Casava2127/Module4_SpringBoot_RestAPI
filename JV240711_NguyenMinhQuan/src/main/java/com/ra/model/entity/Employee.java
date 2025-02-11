package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_name", length = 100, nullable = false)
    private String name;

    @Column(name = "Emp_Birth_Of_Date", nullable = false) // Thêm cột này
    private LocalDate empBirthOfDate; // Hoặc private Date empBirthOfDate;


    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "phone", length = 20, unique = true, nullable = false)
    private String phone;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "avatar", length = 255)
    private String avatar; // Đường dẫn ảnh đại diện

    @Column(name = "status", nullable = false)
    private int status; // 1: Đang làm việc, 0: Nghỉ việc

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
