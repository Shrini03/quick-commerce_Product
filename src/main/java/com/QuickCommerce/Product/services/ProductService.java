package com.QuickCommerce.Product.services;

import com.QuickCommerce.Product.DTO.ProductDTO;
import com.QuickCommerce.Product.exceptions.InvalidArgException;
import com.QuickCommerce.Product.exceptions.NotFoundException;
import com.QuickCommerce.Product.models.Category;
import com.QuickCommerce.Product.models.Product;
import com.QuickCommerce.Product.models.Restaurant;
import com.QuickCommerce.Product.repos.ProductRepo;
import com.QuickCommerce.Product.repos.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;
    @Autowired
    RestaurantRepo restaurantRepo;

    public ProductDTO addProduct(ProductDTO productDTO) throws InvalidArgException {
        productSanityCheck(productDTO);
        Product product = getProduct(productDTO);
        return getProductDTO(productRepo.save(product));
    }

    public ProductDTO getProductById(Long id)throws NotFoundException {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        return getProductDTO(productOptional.get());
    }

    public String deleteProductById(Long id) throws NotFoundException{
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty()){
            throw new NotFoundException("Product not found");
        }
        productRepo.deleteById(id);
        return "Product with id " + id + " deleted";
    }

    public Product getProduct(ProductDTO productDTO) throws InvalidArgException {

        Product product =  new Product(productDTO.getName(),
                                       productDTO.getDescription(),
                                       productDTO.getPrice(), productDTO.getStock(),
                                       productDTO.getImgURL());
        switch (productDTO.getCategoryID()){
            case 0:
                product.setCategory(Category.VEG);
                break;
            case 1:
                product.setCategory(Category.NON_VEG);
                break;
            default:
                throw new InvalidArgException("Invalid Category");
        }
        Optional<Restaurant > restaurantOptional = restaurantRepo.findById(productDTO.getRestaurantID());
        if(restaurantOptional.isEmpty()){
            throw new InvalidArgException("Restaurant not found");
        }
        product.setRestaurant(restaurantOptional.get());

        return product;
    }

    public ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO(product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getImgURL(), 0, 0L);
        productDTO.setCategoryID(product.getCategory().ordinal());
        productDTO.setRestaurantID(product.getRestaurant().getId());
        return productDTO;
    }

    public void productSanityCheck(ProductDTO productDTO) throws InvalidArgException {
        if(productDTO.getName() == null || productDTO.getName().isEmpty()){
            throw new InvalidArgException("Name cannot be empty");
        }
        if(productDTO.getDescription() == null || productDTO.getDescription().isEmpty()){
            throw new InvalidArgException("Description cannot be empty");
        }
        if(productDTO.getPrice() < 0){
            throw new InvalidArgException("Price cannot be empty or negative");
        }
        if(productDTO.getStock() < 0){
            throw new InvalidArgException("Stock cannot be empty or negative");
        }
        if(productDTO.getImgURL() == null || productDTO.getImgURL().isEmpty()){
            throw new InvalidArgException("ImgURL cannot be empty");
        }
        if(productDTO.getRestaurantID() == null){
            throw new InvalidArgException("Restaurant cannot be empty");
        }
    }
}
