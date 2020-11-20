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
    private String product_name;

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
}
