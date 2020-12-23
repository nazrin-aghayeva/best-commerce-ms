package org.signup.ms.payload.response;

import lombok.*;

import java.util.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type= "Bearer";
    private int user_id;
    private String email;
    private List<String> roles;
}
