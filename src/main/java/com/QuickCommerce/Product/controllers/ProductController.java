package com.QuickCommerce.Product.controllers;
import com.QuickCommerce.Product.DTO.ProductDTO;
import com.QuickCommerce.Product.exceptions.InvalidArgException;
import com.QuickCommerce.Product.exceptions.NotFoundException;
import com.QuickCommerce.Product.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) throws InvalidArgException {
        return productService.addProduct(productDTO);
    }

    @PostMapping("/addList")
    public List<ProductDTO> addProductList(@RequestBody List<ProductDTO> productDTOList) throws InvalidArgException{
        return productService.addProductList(productDTOList);
    }

    @GetMapping()
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable Long id) throws NotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/restaurant/{id}")
    public List<ProductDTO> getAllProductsByRestaurant(@PathVariable Long id) throws InvalidArgException{
        return productService.getProductByRestaurantId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) throws NotFoundException {
        return productService.deleteProductById(id);
    }
}
