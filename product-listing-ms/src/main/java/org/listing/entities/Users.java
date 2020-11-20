package org.listing.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_table")
public class Users {
    @Id
    private int user_id;

    private String merchant_type;

    private String merchant_name;

    private String owner_name;

    private String owner_phone_number;

    private String owner_address;

    public String email;

    private String password;

    private String confirm_password;

    private int active;

    private LocalDateTime created_time;

    @OneToMany(targetEntity = Product.class,mappedBy = "user")
    private List<Product> products;

    public void addProduct(Product product){
        products.add(product);
    }
}
