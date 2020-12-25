package org.signup.ms.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.signup.ms.validation.PasswordsEqualConstraint;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@PasswordsEqualConstraint(first = "password", second = "confirm_password", message = "Passwords must match")
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

    private String merchant_type;

    private String merchant_name;

    private String owner_name;

    private String owner_phone_number;

    private String owner_address;

    @Email
    @Column(name = "email", unique = true)
    public String email;

    @Length(min = 6)
    private String password;

    @Length(min = 6)
    private String confirm_password;

    private int active;

    private LocalDateTime created_time;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles= new HashSet<>();
}
