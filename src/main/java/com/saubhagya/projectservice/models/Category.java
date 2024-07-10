package com.saubhagya.projectservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel{
//    private String id;
    private String name;
//    @ManyToOne(mappedBy = "category")
//    private List<Product> products;

}
