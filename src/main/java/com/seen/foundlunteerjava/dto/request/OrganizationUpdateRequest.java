package com.seen.foundlunteerjava.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrganizationUpdateRequest {
    private String name;
    private String address;
    private String phone;
    private String leader;
    private String description;
    private String social;
}
