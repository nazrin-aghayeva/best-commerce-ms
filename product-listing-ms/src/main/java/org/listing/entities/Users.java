package org.listing.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public Users(String merchant_type, String merchant_name, String owner_name, String owner_phone_number, String owner_address, @Email String email, String password, String confirm_password) {
        this.merchant_type = merchant_type;
        this.merchant_name = merchant_name;
        this.owner_name = owner_name;
        this.email = email;
        this.owner_phone_number = owner_phone_number;
        this.owner_address = owner_address;
        this.password = password;
        this.confirm_password = confirm_password;
    }

    @OneToMany(targetEntity = Product.class,mappedBy = "user")
    private List<Product> products;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles= new HashSet<>();
    public void addProduct(Product product){
        products.add(product);
    }

}
