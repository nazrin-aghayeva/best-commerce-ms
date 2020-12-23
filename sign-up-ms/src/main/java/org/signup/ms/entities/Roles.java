package org.signup.ms.entities;

import lombok.*;

import javax.persistence.*;

@Table(name = "role_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private int roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private ERole name;
}
