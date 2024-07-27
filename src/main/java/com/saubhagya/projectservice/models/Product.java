package com.saubhagya.projectservice.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product extends BaseModel implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String name;
    private Double price;
    private String description;
    private String imageURL;
    private Double discountAmount;

    @ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.DETACH})
    private Category category;
}
