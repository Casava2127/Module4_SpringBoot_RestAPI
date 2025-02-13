//package com.ra.controller;
//
//import com.ra.model.dto.product.ProductResponseDTO;
//import com.ra.model.entity.Product;
//import com.ra.service.product.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/products")
//public class ProductController {
//    @Autowired
//    private ProductService productService;
//
//    @GetMapping
//    public ResponseEntity<List<ProductResponseDTO>> findAll() {
////        return ResponseEntity.ok(productService.findAll());
//        List<ProductResponseDTO> products = productService.findAll();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
//
//    // Add more methods as needed
//    // For example, getById, create, update, delete, etc.
//    //CRUD
//    @PostMapping("/create")
//    public ResponseEntity<Product> create(@RequestBody Product product) {
//        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Product> update(@RequestBody Product product) {
//        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        productService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/get/{id}")
//    public ResponseEntity<Product> getById(@PathVariable Long id) {
//        return new ResponseEntity<>(productService.findById(id),
//                HttpStatus.OK);
//    }
//}
