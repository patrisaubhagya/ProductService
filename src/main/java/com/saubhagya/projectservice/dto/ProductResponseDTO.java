package com.saubhagya.projectservice.dto;

import com.saubhagya.projectservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private Product product;
    private String responseMessage;
}
