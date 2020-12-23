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

    @Column(name = "merchant_type")
    private String merchant_type;

    @Column(name = "merchant_name")
    private String merchant_name;

    @Column(name = "owner_name")
    private String owner_name;

    @Column(name = "owner_phone_number")
    private String owner_phone_number;

    @Column(name = "owner_address")
    private String owner_address;

    @Email
    @Column(name = "email", unique = true)
    public String email;

    @Length(min = 6)
    @Column(name = "password")
    private String password;

    @Length(min = 6)
    @Column(name = "confirm_password")
    private String confirm_password;

    @Column(name = "active")
    private int active;

    @Column(name = "created_time")
    private LocalDateTime created_time;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Roles> roles= new HashSet<>();
}
