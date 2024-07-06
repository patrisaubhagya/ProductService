package com.saubhagya.projectservice.dto;

import com.saubhagya.projectservice.models.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ListProductsResponseDTO {
    private List<Product> productList;
    private String ResponseMessage;
}
