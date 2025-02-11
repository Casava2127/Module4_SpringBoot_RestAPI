package com.ra.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    //id, product_name, price, status, category_id ( khoá ngoại)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_name", length = 100,unique = true, nullable = false)
    private String productName;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "image")
    private String image;
    @Column(name = "status", nullable = false)
    private boolean status;
    @ManyToOne
    @JoinColumn(name = "category_id" ,referencedColumnName = "id")// category_id là khoá trong bảng product, tham chiếu
    // toi id của bảng category
    private Category category;

}
