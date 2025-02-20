package com.ra.service.category.imp; // Khai báo package chứa service implementation

import com.ra.model.entity.Category; // Import entity Category để thao tác với đối tượng Category
import com.ra.repository.CategoryRepository; // Import repository để làm việc với database
import com.ra.service.category.CategoryService; // Import interface CategoryService để triển khai các phương thức
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired để inject dependency
import org.springframework.stereotype.Service; // Import Service để đánh dấu class là một service trong Spring Boot

import java.util.List; // Import List để làm việc với danh sách dữ liệu

@Service // Đánh dấu đây là một service component của Spring
public class CategoryServiceImp implements CategoryService { // Triển khai interface CategoryService

    private final CategoryRepository categoryRepository; // Khai báo một biến repository để làm việc với database

    @Autowired // Inject CategoryRepository vào constructor để quản lý dữ liệu
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() { // Lấy danh sách tất cả các danh mục từ database
        return categoryRepository.findAll(); // Gọi phương thức findAll() của repository để lấy toàn bộ danh sách
    }

    @Override
    public Category save(Category category) { // Lưu một danh mục mới vào database
        return categoryRepository.save(category); // Gọi phương thức save() để lưu danh mục
    }

    @Override
    public Category update(Category category) { // Cập nhật thông tin danh mục
        return categoryRepository.save(category); // Gọi phương thức save(), nếu category đã có ID thì sẽ cập nhật thay vì tạo mới
    }

    @Override
    public void delete(Long id) { // Xóa danh mục theo ID
        categoryRepository.deleteById(id); // Gọi phương thức deleteById() để xóa danh mục có ID tương ứng
    }

    @Override
    public Category findById(Long id) { // Tìm danh mục theo ID
        if (categoryRepository.findById(id).isPresent()) { // Kiểm tra nếu danh mục có tồn tại
            return categoryRepository.findById(id).get(); // Lấy giá trị danh mục và trả về
        }
        return null; // Nếu không tìm thấy danh mục, trả về null
    }
}
