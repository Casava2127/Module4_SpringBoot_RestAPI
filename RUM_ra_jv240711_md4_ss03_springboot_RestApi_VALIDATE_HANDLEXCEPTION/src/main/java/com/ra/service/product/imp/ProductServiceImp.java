//package com.ra.service.product.imp; // Package chứa class Service triển khai ProductService
//
//import com.ra.model.dto.product.ProductResponseDTO; // Import DTO để trả dữ liệu ra ngoài API
//import com.ra.model.entity.Product; // Import entity Product (tương ứng với bảng trong database)
//import com.ra.repository.ProductRepository; // Import repository để thao tác với database
//import com.ra.service.product.ProductService; // Import interface ProductService để triển khai
//import org.springframework.beans.factory.annotation.Autowired; // Import @Autowired để inject dependency
//import org.springframework.stereotype.Service; // Import @Service để đánh dấu là Service trong Spring Boot
//import java.util.List; // Import List để làm việc với danh sách dữ liệu
//import java.util.Optional; // Import Optional để xử lý giá trị có thể null
//import java.util.stream.Collectors; // Import Stream API để chuyển đổi dữ liệu
//
//@Service // Đánh dấu đây là Service, giúp Spring quản lý nó như một Bean
//public class ProductServiceImp implements ProductService { // Implement interface ProductService
//
//    @Autowired // Inject ProductRepository để sử dụng các phương thức thao tác với database
//    private ProductRepository productRepository;
//
//    /**
//     * Lấy danh sách tất cả sản phẩm, chuyển đổi từ Entity sang DTO để tránh lộ dữ liệu không cần thiết.
//     * @return List<ProductResponseDTO> - Danh sách sản phẩm dạng DTO
//     */
//    @Override
//    public List<ProductResponseDTO> findAll() {
//        List<Product> products = productRepository.findAll(); // Lấy tất cả sản phẩm từ database (Entity)
//
//        // Chuyển đổi từ Entity → DTO bằng Stream API
//        List<ProductResponseDTO> productResponseDTO = products.stream().map(product ->
//                ProductResponseDTO.builder() // Sử dụng Builder để tạo DTO
//                        .productName(product.getProductName()) // Lấy tên sản phẩm
//                        .price(product.getPrice()) // Lấy giá sản phẩm
//                        .image(product.getImage()) // Lấy đường dẫn hình ảnh sản phẩm
//                        .status(product.isStatus()) // Lấy trạng thái sản phẩm (true/false)
//                        .categoryName(product.getCategory() != null ? product.getCategory().getCategoryName() : null) // Lấy tên danh mục chứa sản phẩm
//                        .build() // Kết thúc builder
//        ).collect(Collectors.toList()); // Chuyển thành danh sách List<ProductResponseDTO>
//
//        return productResponseDTO; // Trả về danh sách DTO đã chuyển đổi
//    }
//
//    /**
//     * Lưu sản phẩm vào database. Nếu sản phẩm đã có ID thì sẽ cập nhật, nếu chưa có ID thì thêm mới.
//     * @param product - Đối tượng sản phẩm cần lưu
//     * @return Product - Sản phẩm sau khi được lưu vào database
//     */
//    @Override
//    public Product save(Product product) {
//        return productRepository.save(product); // Lưu sản phẩm (cập nhật nếu có ID, thêm mới nếu chưa có ID)
//    }
//
//    /**
//     * Xóa sản phẩm theo ID. Nếu không tìm thấy sản phẩm thì ném lỗi.
//     * @param id - ID của sản phẩm cần xóa
//     */
//    @Override
//    public void delete(Long id) {
//        // Kiểm tra sản phẩm có tồn tại trước khi xóa
//        if (!productRepository.existsById(id)) {
//            throw new RuntimeException("Sản phẩm không tồn tại!"); // Ném lỗi nếu không tìm thấy sản phẩm
//        }
//        productRepository.deleteById(id); // Xóa sản phẩm theo ID
//    }
//
//    /**
//     * Tìm sản phẩm theo ID.
//     * @param id - ID của sản phẩm cần tìm
//     * @return Optional<Product> - Sản phẩm nếu tìm thấy, nếu không thì trả về Optional rỗng
//     */
//@Override
//    public Product findById(Long id) {
//        return productRepository.findById(id).orElse(null);
//    }
//}
//
