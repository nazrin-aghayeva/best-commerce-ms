package org.listing.entities.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
 private int status;
 private String message;
 private Object result;
}
