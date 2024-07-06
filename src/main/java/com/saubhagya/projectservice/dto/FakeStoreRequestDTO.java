package com.saubhagya.projectservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreRequestDTO {
    private String title;
    private String price;
    private String description;
    private String image;
    private String category;
}
