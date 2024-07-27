package com.saubhagya.projectservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel implements Serializable {
//    private String id;
    private String name;
//    private String parentCategory;
//    @ManyToOne(mappedBy = "category")
//    @JsonIgnore
//      private List<Product> products;


}
