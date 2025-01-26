package com.QuickCommerce.Product.services;

import com.QuickCommerce.Product.DTO.RestaurantDTO;
import com.QuickCommerce.Product.models.Restaurant;
import com.QuickCommerce.Product.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO){
        Restaurant restaurant = getRestaurant(restaurantDTO);
        return getRestaurantDTO(restaurantRepo.save(restaurant));
    }

    public RestaurantDTO getRestaurantById(Long id){
        return getRestaurantDTO(restaurantRepo.findById(id).get());
    }

    public RestaurantDTO deleteRestaurantById(Long id){
        Restaurant restaurant = restaurantRepo.findById(id).get();
        restaurantRepo.deleteById(id);
        return getRestaurantDTO(restaurant);
    }

    public Restaurant getRestaurant(RestaurantDTO restaurantDTO){
        return new Restaurant(restaurantDTO.getName(),
                              restaurantDTO.getAddress(),
                              restaurantDTO.getPhone(),
                              restaurantDTO.getEmail(),
                              restaurantDTO.getImgURL());
    }
    public RestaurantDTO getRestaurantDTO(Restaurant restaurant){
        return new RestaurantDTO(restaurant.getName(),
                                 restaurant.getAddress(),
                                 restaurant.getPhone(),
                                 restaurant.getEmail(),
                                 restaurant.getImgURL());
    }
}
