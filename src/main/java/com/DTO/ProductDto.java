package com.DTO;

public class ProductDto {
	private Long id;
    private String name;
    private String description;
    private double price;
    private CategoryDTO categoryDTO; // Use CategoryDTO for category details

    public ProductDto(Long id, String name, String description, double price, CategoryDTO categoryDTO) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryDTO = categoryDTO;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public CategoryDTO getCategoryDTO() {
        return categoryDTO;
    }
    public void setCategoryDTO(CategoryDTO categoryDTO) {
        this.categoryDTO = categoryDTO;
    }

    // Add this method to get the categoryId
    public Long getCategoryId() {
        return categoryDTO != null ? categoryDTO.getId() : null;
    }
}   