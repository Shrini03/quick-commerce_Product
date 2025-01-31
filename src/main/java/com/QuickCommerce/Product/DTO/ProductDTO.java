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

    @Override
    public boolean equals(Object other){
        if(other instanceof ProductDTO){
            ProductDTO otherProduct = (ProductDTO) other;
            return this.name.equals(otherProduct.name) &&
                    this.description.equals(otherProduct.description) &&
                    this.price == otherProduct.price &&
                    this.stock == otherProduct.stock &&
                    this.imgURL.equals(otherProduct.imgURL) &&
                    this.categoryID == otherProduct.categoryID &&
                    this.restaurantID == otherProduct.restaurantID;
        }
        return false;
    }
}
