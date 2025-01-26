package com.QuickCommerce.Product.models;

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
public class Product extends Base{
    String name;
    String description;
    Double price;
    int    stock;
    String imgURL;
    @Enumerated(EnumType.ORDINAL)
    Category category;
    @ManyToOne
    Restaurant restaurant;
    @OneToMany(mappedBy = "product")
    List<ProductReview> productReviewList;
}
