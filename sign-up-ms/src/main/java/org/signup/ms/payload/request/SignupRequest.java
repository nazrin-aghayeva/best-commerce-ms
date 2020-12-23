package org.signup.ms.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.util.Set;

@Data
@Getter
@Setter
public class SignupRequest {
    @NotBlank
    private String merchant_type;

    @NotBlank
    private String merchant_name;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone_number;

    @NotBlank
    private String address;

    @NotBlank
    @Min(8)
    private String password;

    @NotBlank
    private String confirm_password;

    private Set<String> roles;
}
