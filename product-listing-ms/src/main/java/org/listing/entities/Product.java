package org.listing.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Accessors(chain = true)
@Entity
@Table(name = "product_listing")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "category")
    private String category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private int price;

    @Min(5)
    @Column(name = "inventory")
    private int inventory;

    @Column(name = "payment_option")
    private String payment_option;

    @Column(name = "delivery_option")
    private String delivery_option;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    public Product(String productName, String category, String description, int price, @Min(5) int inventory, String payment_option, String delivery_option) {
        this.productName = productName;
        this.category = category;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.payment_option = payment_option;
        this.delivery_option = delivery_option;
    }
}
