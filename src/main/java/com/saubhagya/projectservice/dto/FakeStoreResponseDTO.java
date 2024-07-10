package com.saubhagya.projectservice.dto;

import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreResponseDTO {
    private String id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(Long.valueOf(this.id));
        product.setName(this.title);
        product.setPrice(this.price);
        product.setDescription(this.description);
        product.setImageURL(this.image);

        Category category = new Category();

        category.setName(this.category);
//        category.setId(this.id);
        product.setCategory(category);

        return product;
    }
}
