package com.ra.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String fullname;

    @Column(nullable = false)
    private boolean status;  // true: Active, false: Blocked

    @Column(nullable = false)
    private String password;

    @Column(length = 255)
    private String avatar;

    @Column(length = 15, unique = true)
    private String phone;

    @Column(length = 255, nullable = false)
    private String address;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Bạn có thể dùng @PrePersist và @PreUpdate để tự động set createdAt, updatedAt
}
//package com.ra.model.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "users")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@Builder
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long userId;
//
//    @Column(length = 100, nullable = false, unique = true)
//    private String username;
//
//    @Column(length = 255, nullable = false, unique = true)
//    private String email;
//
//    @Column(length = 100, nullable = false)
//    private String fullname;
//
//    @Column(nullable = false)
//    private boolean status;  // true: Active, false: Blocked
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(length = 255)
//    private String avatar;
//
//    @Column(length = 15, unique = true)
//    private String phone;
//
//    @Column(length = 255, nullable = false)
//    private String address;
//
//    @Column(name = "created_at", nullable = false)
//    private LocalDateTime createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    private LocalDateTime updatedAt;
//
//    @Column(name = "is_deleted", nullable = false)
//    private boolean isDeleted;
//
//    // Thêm thuộc tính Role
//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false)
//    private Role role;
//
//    @PrePersist
//    protected void onCreate() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
//}

