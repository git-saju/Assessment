package com.NimapAsseesment.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "products")
public class Product {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    private String description;
	    private double price;

	    @ManyToOne
	    @JoinColumn(name = "category_id", nullable = false)
	    @JsonProperty("category")  // Explicitly name the category during serialization
	    @JsonBackReference
	    private Category category;

	    // Getters and Setters
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

	    public Category getCategory() {
	        return category;
	    }

	    public void setCategory(Category category) {
	        this.category = category;
	    }
	    // New method to return categoryId
	    
	    // Constructors
	    public Product() {}

	    public Product(Long id, String name, String description, double price, Category category) {
	        this.id = id;
	        this.name = name;
	        this.description = description;
	        this.price = price;
	        this.category = category;
	    }
}
