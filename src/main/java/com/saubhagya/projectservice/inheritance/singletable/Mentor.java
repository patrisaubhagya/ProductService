package com.saubhagya.projectservice.inheritance.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@DiscriminatorValue("1")
@Entity
public class Mentor extends User{
    private String company;
    private String avgRating;
}
