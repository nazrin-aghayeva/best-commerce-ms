package org.signup.ms.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_table")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private int user_id;

    @NotBlank(message = "The field shouldn't be empty")
    @Column(name = "merchant_type")
    private String merchant_type;

    @NotBlank(message = "The field shouldn't be empty")
    @Column(name = "merchant_name")
    private String merchant_name;

    @NotBlank(message = "The field shouldn't be empty")
    @Column(name = "owner_name")
    private String owner_name;

    @NotBlank(message = "The field shouldn't be empty")
    @Column(name = "owner_phone_number")
    private String owner_phone_number;

    @NotBlank(message = "The field shouldn't be empty")
    @Column(name = "owner_address")
    private String owner_address;

    @NotBlank(message = "The field shouldn't be empty")
    @Email
    @Column(name = "email")
    public String email;

    @NotBlank(message = "The field shouldn't be empty")
    @Length(min = 6)
    @Column(name = "password")
    private String password;

    @NotBlank(message = "The field shouldn't be empty")
    @Length(min = 6)
    @Column(name = "confirm_password")
    private String confirm_password;

    @Column(name = "active")
    private int active;

    @Column(name = "created_time")
    private LocalDateTime created_time;
}
