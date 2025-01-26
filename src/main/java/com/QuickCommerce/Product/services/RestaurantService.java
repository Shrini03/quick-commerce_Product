package com.QuickCommerce.Product.services;

import com.QuickCommerce.Product.DTO.RestaurantDTO;
import com.QuickCommerce.Product.exceptions.InvalidArgException;
import com.QuickCommerce.Product.exceptions.NotFoundException;
import com.QuickCommerce.Product.models.Restaurant;
import com.QuickCommerce.Product.repos.RestaurantRepo;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) throws InvalidArgException {
        if(restaurantDTO.getName() == null || restaurantDTO.getName().isEmpty()){
            throw new InvalidArgException("Name cannot be empty");
        }
        if(restaurantDTO.getAddress() == null || restaurantDTO.getAddress().isEmpty()){
            throw new InvalidArgException("Address cannot be empty");
        }
        if(restaurantDTO.getPhone() == null || restaurantDTO.getPhone().isEmpty()){
            throw new InvalidArgException("Phone cannot be empty");
        }
        if(restaurantDTO.getEmail() == null || restaurantDTO.getEmail().isEmpty()){
            throw new InvalidArgException("Email cannot be empty");
        }

        Restaurant restaurant = getRestaurant(restaurantDTO);
        return getRestaurantDTO(restaurantRepo.save(restaurant));
    }

    public RestaurantDTO getRestaurantById(Long id) throws NotFoundException {
        Optional<Restaurant> restaurantOptional = restaurantRepo.findById(id);
        if(restaurantOptional.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        return getRestaurantDTO(restaurantOptional.get());
    }

    public RestaurantDTO deleteRestaurantById(Long id) throws NotFoundException{
        Optional<Restaurant> restaurantOptional = restaurantRepo.findById(id);
        if(restaurantOptional.isEmpty()){
            throw new NotFoundException("Restaurant not found");
        }
        restaurantRepo.deleteById(id);
        return getRestaurantDTO(restaurantOptional.get());
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
