package com.ra.repository;

import com.ra.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
//một interface trong Spring Boot sử dụng Spring Data JPA để thao tác với database.

//JpaRepository<Category, Long>
//
//Kế thừa từ JpaRepository, giúp CRUD (Create, Read, Update, Delete) dễ dàng mà không cần viết code SQL.
//Category là kiểu dữ liệu của Entity tương ứng với bảng trong database.
//Long là kiểu dữ liệu của ID trong bảng

//Lợi ích khi dùng JpaRepository
//
//Spring Boot sẽ tự động cung cấp các phương thức cơ bản như:
//save(Category entity) → Thêm hoặc cập nhật dữ liệu.
//findById(Long id) → Tìm kiếm theo ID.
//findAll() → Lấy tất cả bản ghi.
//deleteById(Long id) → Xóa bản ghi theo ID.