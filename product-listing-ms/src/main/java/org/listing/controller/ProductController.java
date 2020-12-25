package org.listing.controller;

import org.listing.entities.Product;
import org.listing.entities.Users;
import org.listing.entities.response.ApiResponse;
import org.listing.exception.InvalidInputException;
import org.listing.exception.InvalidJwtAuthenticationException;
import org.listing.repository.UserRepo;
import org.listing.security.jwt.JwtUtils;
import org.listing.service.impl.ProductListingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    ProductListingImpl productService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepo userRepo;

    @RequestMapping(value = "/products/add", method = RequestMethod.POST )
    public ResponseEntity<Object> addProduct(@Valid @RequestBody Product product, @RequestHeader("Authorization") String token){
        try {
            String username= jwtUtils.getUsernameFromJwt(token);
                productService.addProduct(product, username);
                return ResponseEntity.ok("Product added");

        }
    catch (InvalidJwtAuthenticationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    }

    @RequestMapping(value = "/products/get", method = RequestMethod.GET)
        public ApiResponse<List<Product>> getProducts( @RequestHeader("Authorization") String token){
        String username=jwtUtils.getUsernameFromJwt(token);
        if (userRepo.findByEmail(username).isPresent()) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Product List fetched successfully", productService.getProducts());
        }
        else {
            throw new InvalidJwtAuthenticationException("Invalid credentials");
        }
        }
}
