package com.QuickCommerce.Product.controllers;

import com.QuickCommerce.Product.DTO.RestaurantDTO;
import com.QuickCommerce.Product.exceptions.InvalidArgException;
import com.QuickCommerce.Product.exceptions.NotFoundException;
import com.QuickCommerce.Product.models.Restaurant;
import com.QuickCommerce.Product.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/restaurants")
@RestController
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping("/add")
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) throws InvalidArgException {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PostMapping("/addList")
    public List<RestaurantDTO> addRestaurants(@RequestBody List<RestaurantDTO> restaurantDTOs)throws InvalidArgException {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        for(RestaurantDTO restaurantDTO : restaurantDTOs) {
            restaurantDTOList.add(restaurantService.addRestaurant(restaurantDTO));
        }
        return restaurantDTOList;
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable Long id) throws NotFoundException {
        return restaurantService.getRestaurantById(id);
    }

    @DeleteMapping("/{id}")
    public RestaurantDTO deleteRestaurantById(@PathVariable Long id) throws NotFoundException{
        return restaurantService.deleteRestaurantById(id);
    }
}
