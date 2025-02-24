//package com.ra.model.dto.auth;
//
//import lombok.*;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class SignInRequestDTO {
//    private String username;
//    private String password;
//}
package com.ra.model.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDTO {
    private String username;
    private String password;
}
