package org.listing.service.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.listing.entities.Product;
import org.listing.exception.InvalidInputException;
import org.listing.exception.ResourceNotFoundException;
import org.listing.repository.ProductRepo;
import org.listing.repository.UserRepo;
import org.listing.service.ProductService;

import java.util.List;

@AllArgsConstructor
public class ProductListingImpl  implements ProductService {
    public UserRepo userRepo;
    public ProductRepo productRepo;
    public boolean addProduct( Product product, int userId) {
        if (product.getInventory()<5) throw new InvalidInputException("Inventory must be more than 5");
        if (userRepo.findById(userId).isPresent()) {
            userRepo.findById(userId).get().addProduct(product);
            product.setUser(userRepo.findById(userId).get());
            productRepo.save(product);
            return true;
        }
        throw new ResourceNotFoundException("Merchant not found");
    }

    public List<Product> getProducts(){
       return productRepo.findAll();
    }
}
