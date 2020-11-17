package org.signup.ms.entities.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private int owner_id;
    private String owner_name;
    private String merchant_type;
    private String merchant_name;
    private String owner_address;
    private String owner_phone_number;
    private String email;
    private String password;
    private String confirm_password;
}
