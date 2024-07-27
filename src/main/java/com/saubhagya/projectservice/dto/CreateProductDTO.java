package com.saubhagya.projectservice.dto;

import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Double discount;
    private String categoryName;

    public Product toProduct(){
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageURL(this.imageUrl);
        product.setDiscountAmount(this.discount);

        Category category = new Category();
        category.setName(this.categoryName);

        product.setCategory(category);
        return product;
    }
}
