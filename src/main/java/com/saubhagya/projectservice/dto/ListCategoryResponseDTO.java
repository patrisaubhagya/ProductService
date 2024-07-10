package com.saubhagya.projectservice.dto;

import com.saubhagya.projectservice.models.Category;
import com.saubhagya.projectservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListCategoryResponseDTO {
    private List<Category> categoryList;
    private String ResponseMessage;
}
