package com.example.fetchtest.maper;

import com.example.fetchtest.dto.ProductCreateDto;
import com.example.fetchtest.dto.ProductResponseDto;
import com.example.fetchtest.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {


    public Product toEntity(ProductCreateDto createDto) {
        if (createDto == null) {
            return null;
        }

        Product product = new Product();
        product.setName(createDto.getName());
        product.setDescription(createDto.getDescription());
        product.setPrice(createDto.getPrice());
        product.setShortDescription(createDto.getShortDescription());
        product.setCategory(createDto.getCategory());
        product.setQuantity(createDto.getQuantity());
        product.setBrand(createDto.getBrand());


        return product;
    }


    public ProductResponseDto toResponseDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductResponseDto responseDto = new ProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setName(product.getName());
        responseDto.setDescription(product.getDescription());
        responseDto.setPrice(product.getPrice());
        responseDto.setShortDescription(product.getShortDescription());
        responseDto.setCategory(product.getCategory());
        responseDto.setQuantity(product.getQuantity());
        responseDto.setBrand(product.getBrand());
        responseDto.setCreatedAt(product.getCreatedAt());
        responseDto.setUpdatedAt(product.getUpdatedAt());

        return responseDto;
    }


    public List<ProductResponseDto> toResponseDtoList(List<Product> products) {
        if (products == null) {
            return null;
        }

        return products.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }


    public void updateEntityFromDto(Product product, ProductCreateDto updateDto) {
        if (product == null || updateDto == null) {
            return;
        }

        product.setName(updateDto.getName());
        product.setDescription(updateDto.getDescription());
        product.setPrice(updateDto.getPrice());
        product.setShortDescription(updateDto.getShortDescription());
        product.setCategory(updateDto.getCategory());
        product.setQuantity(updateDto.getQuantity());
        product.setBrand(updateDto.getBrand());

    }
}