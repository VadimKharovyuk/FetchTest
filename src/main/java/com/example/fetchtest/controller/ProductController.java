package com.example.fetchtest.controller;

import com.example.fetchtest.dto.ProductCreateDto;
import com.example.fetchtest.dto.ProductResponseDto;
import com.example.fetchtest.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    /**
     * Создать новый продукт
     */
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductCreateDto createDto) {
        log.info("Creating new product with name: {}", createDto.getName());
        ProductResponseDto createdProduct = productService.createProduct(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Получить все продукты
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        log.info("Fetching all products");
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    /**
     * Получить продукт по ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        log.info("Fetching product with id: {}", id);
        ProductResponseDto product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            log.warn("Product not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }
    }



    /**
     * Удалить продукт
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("Deleting product with id: {}", id);

        ProductResponseDto existingProduct = productService.getProductById(id);
        if (existingProduct == null) {
            log.warn("Product not found for deletion with id: {}", id);
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(id);
        log.info("Product deleted successfully with id: {}", id);
        return ResponseEntity.noContent().build();
    }



    /**
     * Поиск продуктов по названию
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponseDto>> searchProducts(@RequestParam String name) {
        log.info("Searching products by name: {}", name);
         List<ProductResponseDto> products = productService.searchProductsByName(name);
         return ResponseEntity.ok(products);
    }
}