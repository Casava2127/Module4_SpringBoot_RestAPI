package com.ra.controller; // Khai báo package chứa controller

import com.ra.model.dto.product.ProductResponseDTO; // Import DTO để trả về dữ liệu chỉ chứa thông tin cần thiết
import com.ra.model.entity.Product; // Import Entity Product (đại diện cho bảng Product trong database)
import com.ra.service.product.ProductService; // Import Service để gọi các phương thức xử lý nghiệp vụ

import org.springframework.beans.factory.annotation.Autowired; // Import @Autowired để inject dependency
import org.springframework.http.HttpStatus; // Import HttpStatus để trả về mã trạng thái HTTP
import org.springframework.http.ResponseEntity; // Import ResponseEntity để đóng gói HTTP response
import org.springframework.web.bind.annotation.*; // Import các annotation của Spring MVC (Controller, RequestMapping,...)

import java.util.List; // Import List để làm kiểu dữ liệu trả về cho danh sách sản phẩm

@RestController // Đánh dấu đây là một REST Controller trong Spring Boot, giúp xử lý HTTP request/response
@RequestMapping("/api/v1/products") // Định nghĩa đường dẫn gốc của API là "/api/v1/products"
public class ProductController {

    @Autowired // Inject ProductService vào Controller (Dependency Injection)
    private ProductService productService;

    @GetMapping // Xử lý HTTP GET để lấy danh sách tất cả sản phẩm
    public ResponseEntity<List<ProductResponseDTO>> findAll() {
        // Gọi service để lấy danh sách sản phẩm từ database
        List<ProductResponseDTO> products = productService.findAll();
        // Trả về danh sách sản phẩm với mã HTTP 200 OK
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // CRUD - Create, Read, Update, Delete

    @PostMapping("/create") // Xử lý HTTP POST để thêm mới sản phẩm
    public ResponseEntity<Product> create(@RequestBody Product product) {
        // Lưu sản phẩm mới vào database và trả về kết quả với mã HTTP 201 Created
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/update") // Xử lý HTTP PUT để cập nhật sản phẩm
    public ResponseEntity<Product> update(@RequestBody Product product) {
        // Cập nhật sản phẩm trong database và trả về kết quả với mã HTTP 200 OK
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") // Xử lý HTTP DELETE để xóa sản phẩm theo ID
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Gọi service để xóa sản phẩm theo ID
        productService.delete(id);
        // Trả về mã HTTP 200 OK (không có nội dung)
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{id}") // Xử lý HTTP GET để lấy sản phẩm theo ID
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        // Gọi service để tìm sản phẩm theo ID, nếu có thì trả về sản phẩm, nếu không có sẽ throw exception
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }
}
