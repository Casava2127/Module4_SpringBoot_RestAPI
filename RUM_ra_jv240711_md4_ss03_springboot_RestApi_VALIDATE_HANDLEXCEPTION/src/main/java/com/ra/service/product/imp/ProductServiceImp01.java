//package com.ra.service.product.imp;
//
//import com.ra.model.dto.product.ProductRequestDTO;
//import com.ra.model.dto.product.ProductResponseDTO;
//import com.ra.model.entity.Category;
//import com.ra.model.entity.Product;
//import com.ra.repository.CategoryRepository;
//import com.ra.repository.ProductRepository;
//import com.ra.service.product.ProductService;
//import com.ra.service.product.ProductService01;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
////✅ Nhận DTO từ Controller, chuyển đổi thành Entity để lưu vào Repository.
////✅ Nhận Entity từ Repository, chuyển đổi thành DTO để trả về Controller.
//
//
//@Service // Đánh dấu đây là Service để Spring quản lý tự động
//public class ProductServiceImp01 implements ProductService01 {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Autowired
//    private CategoryRepository categoryRepository; // Repository để lấy thông tin Category
//
//    /**
//     * Lấy danh sách tất cả sản phẩm, chuyển đổi từ Entity sang DTO.
//     * @return List<ProductResponseDTO> - Danh sách sản phẩm dạng DTO
//     */
//    @Override
//    public List<ProductResponseDTO> findAll() {
//        List<Product> products = productRepository.findAll(); // Lấy tất cả sản phẩm từ DB
//
//        // Chuyển đổi từ Entity → DTO bằng Stream API
//        return products.stream()
//                .map(this::convertToDTO) // Gọi hàm chuyển đổi từ Entity → DTO
//                .collect(Collectors.toList());
//    }
//
//    /**
//     * Tìm sản phẩm theo ID, trả về dưới dạng DTO.
//     * @param id - ID của sản phẩm cần tìm
//     * @return ProductResponseDTO - DTO chứa thông tin sản phẩm (nếu tìm thấy), null nếu không có
//     */
//    @Override
//    public ProductResponseDTO findById(Long id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        return optionalProduct.map(this::convertToDTO).orElse(null);
//    }
//
//    /**
//     * Thêm mới sản phẩm từ DTO.
//     * @param productDTO - DTO chứa thông tin sản phẩm cần thêm
//     * @return ProductResponseDTO - DTO chứa thông tin sản phẩm sau khi lưu
//     */
//    @Override
//    public ProductResponseDTO save(ProductRequestDTO productDTO) {
//        Product product = convertToEntity(productDTO); // Chuyển từ DTO → Entity
//        Product savedProduct = productRepository.save(product);
//        return convertToDTO(savedProduct); // Trả về DTO sau khi lưu thành công
//    }
//
//    /**
//     * Cập nhật thông tin sản phẩm.
//     * @param id - ID sản phẩm cần cập nhật
//     * @param productDTO - DTO chứa thông tin mới của sản phẩm
//     * @return ProductResponseDTO - DTO chứa thông tin sản phẩm sau khi cập nhật
//     */
//    @Override
//    public ProductResponseDTO update(Long id, ProductRequestDTO productDTO) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (!optionalProduct.isPresent()) {
//            return null; // Hoặc ném exception nếu muốn
//        }
//
//        Product existingProduct = optionalProduct.get();
//        existingProduct.setProductName(productDTO.getProductName());
//        existingProduct.setPrice(productDTO.getPrice());
//        existingProduct.setImage(productDTO.getImage());
//        existingProduct.setStatus(productDTO.isStatus());
//
//        // Cập nhật Category nếu có ID danh mục mới
//        if (productDTO.getCategoryId() != null) {
//            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
//            category.ifPresent(existingProduct::setCategory);
//        }
//
//        Product updatedProduct = productRepository.save(existingProduct);
//        return convertToDTO(updatedProduct);
//    }
//
//    /**
//     * Xóa sản phẩm theo ID.
//     * @param id - ID của sản phẩm cần xóa
//     * @return true nếu xóa thành công, false nếu không tìm thấy sản phẩm
//     */
//    @Override
//    public boolean delete(Long id) {
//        if (!productRepository.existsById(id)) {
//            return false;
//        }
//        productRepository.deleteById(id);
//        return true;
//    }
//
//    // ======================= Chuyển đổi Entity ⇆ DTO ========================
//
//    /**
//     * Chuyển từ Entity → DTO.
//     * @param product - Đối tượng Entity cần chuyển đổi
//     * @return ProductResponseDTO - DTO chứa thông tin cần thiết của sản phẩm
//     */
//    private ProductResponseDTO convertToDTO(Product product) {
//        return ProductResponseDTO.builder()
//                .productName(product.getProductName())
//                .price(product.getPrice())
//                .image(product.getImage())
//                .status(product.isStatus())
//                .categoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null)
//                .build();
//    }
//
//    /**
//     * Chuyển từ DTO → Entity.
//     * @param productDTO - DTO chứa thông tin sản phẩm từ request
//     * @return Product - Đối tượng Entity để lưu vào database
//     */
//    private Product convertToEntity(ProductRequestDTO productDTO) {
//        Product product = new Product();
//        product.setProductName(productDTO.getProductName());
//        product.setPrice(productDTO.getPrice());
//        product.setImage(productDTO.getImage());
//        product.setStatus(productDTO.isStatus());
//
//        // Tìm category theo ID nếu có
//        if (productDTO.getCategoryId() != null) {
//            Optional<Category> category = categoryRepository.findById(productDTO.getCategoryId());
//            category.ifPresent(product::setCategory);
//        }
//
//        return product;
//    }
//}
