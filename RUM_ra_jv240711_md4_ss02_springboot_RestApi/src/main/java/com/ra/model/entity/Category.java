package com.ra.model.entity;

import jakarta.persistence.*;


import lombok.*;

@Entity // cai nay la JPA annotation
@Table(name = "categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category_name", length = 100,unique = true, nullable = false)
    private String categoryName;
    @Column(name = "category_status")
    private boolean categoryStatus;

    public boolean getCategoryStatus() {
        return categoryStatus;
    }
}
