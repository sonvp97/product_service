package com.cg.dto;

import com.cg.entity.Category;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String image;
    private int quantity;
    private String category_name;
}