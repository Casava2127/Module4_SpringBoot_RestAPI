package com.ra.service.product;
import com.ra.model.dto.product.ProductRequestDTO;
import com.ra.model.dto.product.ProductResponseDTO;
import com.ra.model.entity.Product;
import com.ra.repository.CategoryRepository;
import com.ra.repository.ProductRepository;
import com.ra.service.UploadService;
import com.ra.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private CategoryService categoryService;
    @Override
    public List<ProductResponseDTO> findAll() {

        List<Product> products = productRepository.findAll();
        // convert Entity = > DTO
        List<ProductResponseDTO> responseDTOS;
        responseDTOS = products.stream().map(product ->
                ProductResponseDTO.builder()
                        .id(product.getId())
                        .productName(product.getProductName())
                        .price(product.getPrice())
                        .image(product.getImage())
                        .status(product.getStatus())
                        .categoryName(product.getCategory().getCategoryName())
                        .build()
                ).collect(Collectors.toList());
        return responseDTOS;
    }
    @Override
    public Page<ProductResponseDTO> pagination(Pageable pageable) {
        List<Product> products = productRepository.findAll(pageable).getContent();
        List<ProductResponseDTO> responseDTOS;
        responseDTOS = products.stream().map(product -> ProductResponseDTO.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .price(product.getPrice())
                .image(product.getImage())
                .status(product.getStatus())
                .categoryName(product.getCategory().getCategoryName())
                .build()).collect(Collectors.toList());
        return new PageImpl<>(responseDTOS, pageable, products.size());
    }
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public ProductResponseDTO save(ProductRequestDTO productDTO) {

        // upload file
        String fileName = uploadService.uploadFile(productDTO.getImage());

        // convert từ DTO = > Entity
        Product product = Product.builder()
                .productName(productDTO.getProductName())
                .price(productDTO.getPrice())
                .image(fileName)
                .status(productDTO.getStatus())
                .category(categoryService.findById(productDTO.getCategoryId()))
                .build();
        Product productNew = productRepository.save(product);
        // convert từ entity => DTO
        return ProductResponseDTO.
                builder()
                .id(productNew.getId())
                .productName(productNew.getProductName())
                .image(productNew.getImage())
                .price(productNew.getPrice())
                .status(productNew.getStatus())
                .categoryName(productNew.getCategory().getCategoryName())
                .build();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


}
