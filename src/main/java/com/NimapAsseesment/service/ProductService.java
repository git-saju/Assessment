package com.NimapAsseesment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.NimapAsseesment.entity.Category;
import com.NimapAsseesment.entity.Product;
import com.NimapAsseesment.repository.CategoryRepository;
import com.NimapAsseesment.repository.ProductRepository;
import com.DTO.ProductDto;
import com.DTO.CategoryDTO;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    // fetching all teh products with pagination
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(product -> {
            CategoryDTO categoryDTO = new CategoryDTO(product.getCategory().getId(), product.getCategory().getName());
            return new ProductDto(product.getId(), product.getName(), product.getDescription(), product.getPrice(), categoryDTO);
        });
    }

    // converting dto to entity and save 
    public ProductDto saveProduct(ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        Product savedProduct = productRepository.save(product);

        CategoryDTO categoryDTO = new CategoryDTO(savedProduct.getCategory().getId(), savedProduct.getCategory().getName());
        return new ProductDto(savedProduct.getId(), savedProduct.getName(), savedProduct.getDescription(), savedProduct.getPrice(), categoryDTO);
    }

    // Updating the  product
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        CategoryDTO categoryDTO = new CategoryDTO(updatedProduct.getCategory().getId(), updatedProduct.getCategory().getName());
        return new ProductDto(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice(), categoryDTO);
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
