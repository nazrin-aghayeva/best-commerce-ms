package org.listing.config;

import org.listing.entities.Product;
import org.listing.repository.ProductRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AutoRun {
    private ProductRepo productRepo;

    public AutoRun(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Bean
    public CommandLineRunner addAll(){
        return args -> {
            productRepo.saveAll(Arrays.asList(
                    new Product("New Apple MacBook Pro", "Tech", "16-inch, 16GB RAM, 1TB Storage, 2.3GHz Intel Core i9",2630, 7, "By Card", "Express"),
                    new Product("Lenovo Legion 5 Gaming Laptop", "Tech","15.6\" FHD (1920x1080) IPS Screen, AMD Ryzen 7 4800H Processor, 16GB DDR4, 512GB SSD, NVIDIA GTX 1660Ti, Windows 10, 82B1000AUS", 980, 6, "By Card/Cash","Express")
            ));
        };
    }
}
