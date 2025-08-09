package com.example.fetchtest.service;

import com.example.fetchtest.dto.ProductCreateDto;
import com.example.fetchtest.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {

    ProductResponseDto createProduct(ProductCreateDto createDto);

    void deleteProduct(Long id);

    List<ProductResponseDto> getAllProducts();
    ProductResponseDto getProductById(Long id);


    List<ProductResponseDto> searchProductsByName(String name);
}
