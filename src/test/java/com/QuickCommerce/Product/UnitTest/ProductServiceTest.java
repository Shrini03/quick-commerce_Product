package com.QuickCommerce.Product.UnitTest;

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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks // Injecting service
    ProductService productService;
    @Mock // Mocking repository
    ProductRepo productRepo;
    @Mock // Mocking repository
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
