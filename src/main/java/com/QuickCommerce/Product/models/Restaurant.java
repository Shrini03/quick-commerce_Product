package com.QuickCommerce.Product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Restaurant extends Base{
    String name;
    String address;
    String phone;
    String email;
    String imgURL;
    @OneToMany(mappedBy = "restaurant")
    List<RestaurantReview> restaurantReviewList;
    @OneToMany(mappedBy = "restaurant")
    List<Product> productList;

    public Restaurant(String name, String address, String phone, String email, String imgURL) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.imgURL = imgURL;
    }
}
