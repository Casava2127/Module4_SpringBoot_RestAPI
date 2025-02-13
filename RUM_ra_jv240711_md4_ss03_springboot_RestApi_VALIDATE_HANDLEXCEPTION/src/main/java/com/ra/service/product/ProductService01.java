package com.ra.service.product;

import com.ra.model.dto.product.ProductRequestDTO;
import com.ra.model.dto.product.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService01 {
    /**
     * Lấy danh sách tất cả sản phẩm
     * @return List<ProductResponseDTO> - Danh sách sản phẩm dạng DTO
     */
    List<ProductResponseDTO> findAll();

    /**
     * Tìm sản phẩm theo ID, trả về dưới dạng DTO
     * @param id - ID của sản phẩm
     * @return ProductResponseDTO - Sản phẩm nếu tìm thấy, null nếu không có
     */
    Optional<ProductResponseDTO> findById(Long id);

    /**
     * Thêm mới sản phẩm từ DTO
     * @param productDTO - DTO chứa thông tin sản phẩm cần thêm
     * @return ProductResponseDTO - Sản phẩm sau khi được lưu
     */
    ProductResponseDTO save(ProductRequestDTO productDTO);

    /**
     * Cập nhật sản phẩm
     * @param id - ID sản phẩm cần cập nhật
     * @param productDTO - DTO chứa thông tin mới của sản phẩm
     * @return ProductResponseDTO - Sản phẩm sau khi cập nhật
     */
    ProductResponseDTO update(Long id, ProductRequestDTO productDTO);

    /**
     * Xóa sản phẩm theo ID
     * @param id - ID của sản phẩm cần xóa
     * @return boolean - `true` nếu xóa thành công, `false` nếu sản phẩm không tồn tại
     */
    boolean delete(Long id);
}
