package com.QuickCommerce.Product.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    String name;
    String description;
    double price;
    int stock;
    String imgURL;
    int categoryID;
    Long restaurantID;
}
