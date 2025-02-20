package com.ra.controller; // Định nghĩa package của controller

import com.ra.model.dto.DataError; // Import lớp DataError để xử lý lỗi
import com.ra.model.entity.Category; // Import entity Category (tương ứng với bảng Category trong database)
import com.ra.service.category.CategoryService; // Import service để thao tác với dữ liệu
import org.springframework.beans.factory.annotation.Autowired; // Import annotation để tự động inject dependencies
import org.springframework.http.HttpStatus; // Import HttpStatus để trả về mã trạng thái HTTP
import org.springframework.http.ResponseEntity; // Import ResponseEntity để tạo phản hồi HTTP
import org.springframework.web.bind.annotation.*; // Import các annotation của Spring Boot
import java.util.List; // Import List để làm việc với danh sách dữ liệu
// dinh nghia cac API endPoint
@RestController // Đánh dấu lớp này là RESTful Controller, xử lý API
@RequestMapping("/api/v1/categories") // Định nghĩa URL gốc cho tất cả API trong controller này
public class CategoryController {

    @Autowired // Tự động inject bean CategoryService vào controller
    private CategoryService categoryService;

    // 📌 METHOD: GET - Lấy tất cả danh mục
    // URL: localhost:8080/api/v1/categories
    @GetMapping // Xử lý request GET "/api/v1/categories"
    public ResponseEntity<List<Category>> index() {
        List<Category> categories = categoryService.findAll(); // Gọi service để lấy danh sách category
        return new ResponseEntity<>(categories, HttpStatus.OK); // Trả về danh sách category và mã 200 (OK)
    }

    // 📌 METHOD: GET - Lấy danh mục theo ID
    // URL: localhost:8080/api/v1/categories/{id}
    @GetMapping("/{id}") // Xử lý request GET "/api/v1/categories/{id}"
    public ResponseEntity<?> show(@PathVariable Long id) { // Dấu ? có thể trả về nhiều kiểu dữ liệu khác nhau
        Category category = categoryService.findById(id); // Gọi service để tìm category theo ID
        if (category == null) {
            // Nếu không tìm thấy, trả về lỗi 404 với thông báo
            return new ResponseEntity<>(new DataError("category bị lỗi rồi nhaaa", 404), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK); // Trả về category và mã 200 (OK)
    }

    // 📌 METHOD: POST - Thêm mới danh mục
    // URL: localhost:8080/api/v1/categories/add
    @PostMapping("/add") // Xử lý request POST "/api/v1/categories/add"
    public ResponseEntity<Category> create(@RequestBody Category category) { // vì gửi từ body của PostMan
        Category newCategory = categoryService.save(category); // Gọi service để lưu category mới
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED); // Trả về dữ liệu mới và mã 201 (Created)
    }

    // 📌 METHOD: PUT - Cập nhật danh mục
    // URL: localhost:8080/api/v1/categories/update/{id}
    // Request body: {
    //    "categoryName": "categoryUpdated",
    //    "categoryStatus": false
    // }
    // Response: Trả về category đã cập nhật hoặc 404 nếu không tìm thấy
    @PutMapping("/update/{id}") // Xử lý request PUT "/api/v1/categories/update/{id}"
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Category existingCategory = categoryService.findById(id); // Kiểm tra category có tồn tại không
        if (existingCategory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về mã 404 nếu không tìm thấy
        }
        // Cập nhật thông tin
        existingCategory.setCategoryName(category.getCategoryName());
        existingCategory.setCategoryStatus(category.getCategoryStatus());
        Category updatedCategory = categoryService.save(existingCategory); // Lưu thay đổi
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK); // Trả về category đã cập nhật và mã 200 (OK)
    }

    // 📌 METHOD: DELETE - Xóa danh mục
    // URL: localhost:8080/api/v1/categories/delete/{id}
    // Response: 204 No Content nếu xóa thành công, 404 nếu không tồn tại
    @DeleteMapping("/delete/{id}") // Xử lý request DELETE "/api/v1/categories/delete/{id}"
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category category = categoryService.findById(id); // Kiểm tra category có tồn tại không
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về mã 404 nếu không tìm thấy
        }
        categoryService.delete(id); // Gọi service để xóa category
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Trả về mã 204 (No Content) nếu xóa thành công
    }
}
