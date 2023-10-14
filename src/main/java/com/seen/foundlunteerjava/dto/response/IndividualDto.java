package com.seen.foundlunteerjava.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class IndividualDto {
    private final UserAppDto user;
    private final LocalDate birthOfDate;
    private final String description;
    private final String social;

}
