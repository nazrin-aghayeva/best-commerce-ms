package org.listing.controller;

import org.listing.entities.Product;
import org.listing.entities.response.ApiResponse;
import org.listing.exception.InvalidInputException;
import org.listing.exception.InvalidJwtAuthenticationException;
import org.listing.service.impl.ProductListingImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    ProductListingImpl productService;

    @RequestMapping(value = "/products/add", method = RequestMethod.POST )
    public ResponseEntity<Object> addProduct(@Valid @RequestBody Product product, @RequestHeader("Authorization") String token){
        try {

            try {
//                productService.addProduct(product, userId.get());
                return ResponseEntity.ok("Product added");
            }
            catch (InvalidInputException e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }
    catch (InvalidJwtAuthenticationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    }

    @RequestMapping(value = "/products/get", method = RequestMethod.GET)
        public ApiResponse<List<Product>> getProducts(){
            return new ApiResponse<>(HttpStatus.OK.value(), "Product List fetched successfully", productService.getProducts());
        }

}
