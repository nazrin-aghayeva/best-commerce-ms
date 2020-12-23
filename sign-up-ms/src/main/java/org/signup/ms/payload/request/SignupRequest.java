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
    private String email;

    @NotBlank
    @Min(8)
    private String password;

    private Set<String> roles;
}
