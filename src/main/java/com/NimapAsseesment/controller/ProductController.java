package com.NimapAsseesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.NimapAsseesment.entity.Product;
import com.NimapAsseesment.repository.CategoryRepository;
import com.NimapAsseesment.repository.ProductRepository;
import com.NimapAsseesment.service.ProductService;
import com.DTO.ProductDto;
import com.DTO.CategoryDTO;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;

    // Get all products with pagination
    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return productService.getAllProducts(PageRequest.of(page, size));
    }

    // Create a product
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProductDto = productService.saveProduct(productDto);
        return new ResponseEntity<>(savedProductDto, HttpStatus.CREATED);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

        // Convert Product to ProductDto with CategoryDTO
        CategoryDTO categoryDTO = new CategoryDTO(product.getCategory().getId(), product.getCategory().getName());
        return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), categoryDTO);
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        ProductDto updatedProductDto = productService.updateProduct(id, productDto);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    // Delete product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
