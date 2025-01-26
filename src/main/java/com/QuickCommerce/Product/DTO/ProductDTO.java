package com.QuickCommerce.Product.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    String name;
    String description;
    double price;
    int stock;
    String imgURL;
    int categoryID;
    int restaurantID;
}
