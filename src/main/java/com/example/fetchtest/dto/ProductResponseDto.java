package com.example.fetchtest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String shortDescription;
    private String category;
    private Integer quantity;
    private String brand;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}