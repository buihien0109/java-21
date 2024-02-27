package vn.techmaster.demothymeleafspringsecurity.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
    String name;
    String email;
    String password;
    String confirmPassword;
}
