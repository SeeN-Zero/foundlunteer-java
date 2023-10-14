package com.seen.foundlunteerjava.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OrganizationDto {
    private final UserAppDto user;
    private final String leader;
    private final String description;
    private final String social;
}
