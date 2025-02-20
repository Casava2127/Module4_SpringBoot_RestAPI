package com.ra.service.product.imp; // Khai báo package chứa service implementation cho product

import com.ra.model.dto.product.ProductRequestDTO; // Import DTO cho yêu cầu thêm hoặc cập nhật sản phẩm
import com.ra.model.dto.product.ProductResponseDTO; // Import DTO cho phản hồi trả về khi lấy thông tin sản phẩm
import com.ra.model.entity.Category; // Import entity Category để làm việc với danh mục sản phẩm
import com.ra.model.entity.Product; // Import entity Product để thao tác với dữ liệu sản phẩm
import com.ra.repository.CategoryRepository; // Import repository để truy xuất thông tin category từ DB
import com.ra.repository.ProductRepository; // Import repository để truy xuất thông tin product từ DB
import com.ra.service.product.ProductService; // Import interface ProductService để triển khai
import com.ra.service.product.ProductService01; // Import interface ProductService01 mà class này thực hiện
import org.springframework.beans.factory.annotation.Autowired; // Import Autowired để inject các dependency vào class
import org.springframework.stereotype.Service; // Đánh dấu class là service trong Spring Boot

import java.util.List; // Import List để thao tác với danh sách
import java.util.Optional; // Import Optional để xử lý giá trị có thể null
import java.util.stream.Collectors; // Import Collectors để thao tác với Stream API

@Service // Đánh dấu class này là một service component trong Spring Boot
public class ProductServiceImp01 implements ProductService01 { // Class này triển khai ProductService01

    @Autowired
    private ProductRepository productRepository; // Inject ProductRepository để làm việc với bảng sản phẩm trong DB

    @Autowired
    private CategoryRepository categoryRepository; // Inject CategoryRepository để làm việc với bảng danh mục sản phẩm

    /**
     * Lấy danh sách tất cả sản phẩm, chuyển đổi từ Entity sang DTO.
     * @return List<ProductResponseDTO> - Danh sách sản phẩm dạng DTO
     */
    @Override
    public List<ProductResponseDTO> findAll() {
        List<Product> products = productRepository.findAll(); // Lấy tất cả sản phẩm từ DB

        // Chuyển đổi từ Entity → DTO bằng Stream API
        return products.stream()
                .map(this::convertToDTO) // Gọi phương thức chuyển đổi từ Entity → DTO
                .collect(Collectors.toList()); // Thu thập các DTO vào danh sách và trả về
    }

    /**
     * Tìm sản phẩm theo ID, trả về dưới dạng DTO.
     * @param id - ID của sản phẩm cần tìm
     * @return ProductResponseDTO - DTO chứa thông tin sản phẩm (nếu tìm thấy), null nếu không có
     */
    @Override
    public ProductResponseDTO findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id); // Tìm sản phẩm theo ID
        return optionalProduct.map(this::convertToDTO).orElse(null); // Nếu sản phẩm tồn tại, chuyển thành DTO, nếu không thì trả về null
    }

    /**
     * Thêm mới sản phẩm từ DTO.
     * @param productDTO - DTO chứa thông tin sản phẩm cần thêm
     * @return ProductResponseDTO - DTO chứa thông tin sản phẩm sau khi lưu
     */
    @Override
    public ProductResponseDTO save(ProductRequestDTO productDTO) {
        Product product = convertToEntity(productDTO); // Chuyển từ DTO → Entity
        Product savedProduct = productRepository.save(product); // Lưu sản phẩm vào DB
        return convertToDTO(savedProduct); // Trả về DTO của sản phẩm đã được lưu
    }

    /**
     * Cập nhật thông tin sản phẩm.
     * @param id - ID sản phẩm cần cập nhật
     * @param productDTO - DTO chứa thông tin mới của sản phẩm
     * @return ProductResponseDTO - DTO chứa thông tin sản phẩm sau khi cập nhật
     */
    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id); // Kiểm tra sản phẩm có tồn tại không
        if (!optionalProduct.isPresent()) { // Nếu không tìm thấy sản phẩm
            return null; // Trả về null (hoặc có thể ném exception nếu muốn)
        }

        Product existingProduct = optionalProduct.get(); // Lấy sản phẩm hiện tại
        // Cập nhật các trường thông tin từ DTO vào sản phẩm
        existingProduct.setProductName(productDTO.getProductName());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setImage(productDTO.getImage());
        existingProduct.setStatus(productDTO.isStatus());

        // Cập nhật Category nếu có ID danh mục mới trong DTO
        if (productDTO.getCategoryId() != null) {
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
            category.ifPresent(existingProduct::setCategory); // Nếu tìm thấy danh mục, gán vào sản phẩm
        }

        Product updatedProduct = productRepository.save(existingProduct); // Lưu sản phẩm đã cập nhật vào DB
        return convertToDTO(updatedProduct); // Trả về DTO của sản phẩm đã cập nhật
    }

    /**
     * Xóa sản phẩm theo ID.
     * @param id - ID của sản phẩm cần xóa
     * @return true nếu xóa thành công, false nếu không tìm thấy sản phẩm
     */
    @Override
    public boolean delete(Long id) {
        if (!productRepository.existsById(id)) { // Kiểm tra sản phẩm có tồn tại không
            return false; // Nếu không tồn tại, trả về false
        }
        productRepository.deleteById(id); // Xóa sản phẩm theo ID
        return true; // Trả về true nếu xóa thành công
    }

    // ======================= Chuyển đổi Entity ⇆ DTO ========================

    /**
     * Chuyển từ Entity → DTO.
     * @param product - Đối tượng Entity cần chuyển đổi
     * @return ProductResponseDTO - DTO chứa thông tin cần thiết của sản phẩm
     */
    private ProductResponseDTO convertToDTO(Product product) {
        return ProductResponseDTO.builder() // Sử dụng builder để tạo DTO
                .productName(product.getProductName())
                .price(product.getPrice())
                .image(product.getImage())
                .status(product.isStatus())
                .categoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null) // Nếu có category thì lấy tên danh mục
                .build(); // Xây dựng và trả về DTO
    }

    /**
     * Chuyển từ DTO → Entity.
     * @param productDTO - DTO chứa thông tin sản phẩm từ request
     * @return Product - Đối tượng Entity để lưu vào database
     */
    private Product convertToEntity(ProductRequestDTO productDTO) {
        Product product = new Product(); // Tạo mới đối tượng Product
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setImage(productDTO.getImage());
        product.setStatus(productDTO.isStatus());

        // Tìm category theo ID nếu có trong DTO
        if (productDTO.getCategoryId() != null) {
            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
            category.ifPresent(product::setCategory); // Nếu tìm thấy category, gán vào sản phẩm
        }

        return product; // Trả về đối tượng Product
    }
}
