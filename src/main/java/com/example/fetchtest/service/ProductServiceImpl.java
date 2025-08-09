package com.example.fetchtest.service;

import com.example.fetchtest.dto.ProductCreateDto;
import com.example.fetchtest.dto.ProductResponseDto;
import com.example.fetchtest.maper.ProductMapper;
import com.example.fetchtest.model.Product;
import com.example.fetchtest.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private  final ProductMapper productMapper;
    private final ProductRepository productRepository ;


    public ProductResponseDto createProduct(ProductCreateDto createDto) {
        Product product = productMapper.toEntity(createDto);
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponseDto(savedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return productMapper.toResponseDtoList(products);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toResponseDto)
                .orElse(null);
    }

    @Override
    public List<ProductResponseDto> searchProductsByName(String name) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(name);
        return productMapper.toResponseDtoList(products);
    }

}
