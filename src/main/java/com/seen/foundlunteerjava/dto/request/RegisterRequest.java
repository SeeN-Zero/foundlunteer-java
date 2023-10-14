package com.seen.foundlunteerjava.dto.request;

import com.seen.foundlunteerjava.dto.validation.EnumValidation;
import com.seen.foundlunteerjava.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email not valid")
    private String email;
    @NotBlank(message = "Password cannot be empty")
    private String password;
    @NotBlank(message = "name cannot be empty")
    private String name;
    @NotBlank(message = "Address cannot be empty")
    private String address;
    @NotBlank(message = "Phone cannot be empty")
    private String phone;
    @NotBlank(message = "Role cannot be empty")
    @EnumValidation(enumClass = Role.class, message = "Role must be Organization or Individual")
    private String role;
}
