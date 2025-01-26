package com.QuickCommerce.Product.controllers;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
@RestController
public class ProductController {

//    @PostMapping("/add")
//    public String addProduct(@RequestBody ProductDTO productDTO) {
//
//    }
//
//    @GetMapping("/{id}")
//    public ProductDTO getProductById(Long id) {
//       return null;
//    }


    @GetMapping("/isAlive")
    public String isAlive() {
        return "Yes";
    }
}
