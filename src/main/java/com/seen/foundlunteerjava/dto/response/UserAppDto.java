package com.seen.foundlunteerjava.dto.response;

import com.seen.foundlunteerjava.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserAppDto {
    private final String email;
    private final String name;
    private final String address;
    private final String phone;
    private final Role role;
}
