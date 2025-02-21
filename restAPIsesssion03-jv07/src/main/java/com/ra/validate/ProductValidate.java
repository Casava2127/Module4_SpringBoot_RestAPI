package com.ra.validate;

import com.ra.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductValidate implements ConstraintValidator<ProductUnique,String> {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return !productRepository.existsProductByProductName(value);
    }
}
