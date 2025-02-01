package com.QuickCommerce.Product.UnitTest;

import com.QuickCommerce.Product.DTO.ProductDTO;
import com.QuickCommerce.Product.controllers.ProductController;
import com.QuickCommerce.Product.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    ProductDTO productDTO = new ProductDTO(
            "Product",
            "Test Product",
            100.0,
            100,
            "Image URL",
            1,
            1L);
    @Test
    public void addProductTest() throws Exception {
        when(productService.addProduct(any())).thenReturn(productDTO);

        String response =    mockMvc.perform(post("/products/add")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(productDTO)))
                                    .andExpect(status().isOk())
                                    .andReturn()
                                    .getResponse()
                                    .getContentAsString();

        ProductDTO responseDTO = objectMapper.readValue(response, ProductDTO.class);
        Assertions.assertEquals(responseDTO, productDTO);

    }


    @Test
    public void getProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(productDTO);
        String response = mockMvc.perform(get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(response, objectMapper.writeValueAsString(productDTO));
    }

    @Test
    public void deleteProductById() throws Exception {
        when(productService.deleteProductById(anyLong())).thenReturn("Product Deleted Successfully");
        String response = mockMvc.perform(delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Assertions.assertEquals(response, "Product Deleted Successfully");
    }
}
