package com.QuickCommerce.Product.ProductService;

import com.QuickCommerce.Product.DTO.ProductDTO;
import com.QuickCommerce.Product.exceptions.InvalidArgException;
import com.QuickCommerce.Product.exceptions.NotFoundException;
import com.QuickCommerce.Product.models.Category;
import com.QuickCommerce.Product.models.Product;
import com.QuickCommerce.Product.models.Restaurant;
import com.QuickCommerce.Product.repos.ProductRepo;
import com.QuickCommerce.Product.repos.RestaurantRepo;
import com.QuickCommerce.Product.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    ProductService productService;
    @MockitoBean
    ProductRepo productRepo;
    @MockitoBean
    RestaurantRepo resturantRepo;

    Restaurant restaurant = new Restaurant(
            "Restaurant",
            "Test Restaurant",
            "Test Address",
            "Test Image URL",
            "Test Description",
            null,
            null);
    Product product = new Product(
            "Product",
            "Test Product",
            100.0,
            100,
            "Image URL",
            Category.VEG,
            restaurant,
            null);

    @Test
    public void checkExceptionWhenProductNameIsNull() {
        ProductDTO productDTO = new ProductDTO(
                null,
                "Test Category",
                100.0,
                10,
                "Test Image URL",
                1,
                1L);

        Assertions.assertThrows(InvalidArgException.class,
                ()->productService.addProduct(productDTO));
    }
    @Test
    public void checkIfGetProductByIdReturnsProductDTO() throws NotFoundException {


        when(productRepo.findById(anyLong())).thenReturn(Optional.of(product));
        ProductDTO referenceProductDTO = productService.getProductDTO(product);
        ProductDTO actualProductDTO = productService.getProductById(1L);
        Assertions.assertEquals(referenceProductDTO,actualProductDTO);
    }

    @Test
    public void checkIfDeleteProductByIdReturnsDeleteMessageOnSuccess() throws NotFoundException{
        when(productRepo.findById(anyLong())).thenReturn(Optional.of(product));
        doNothing().when(productRepo).deleteById(anyLong());
        Assertions.assertEquals("Product with id 1 deleted",
                productService.deleteProductById(1L));
    }

}
