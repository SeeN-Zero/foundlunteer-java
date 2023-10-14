package com.seen.foundlunteerjava.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RegisteredJobsDto {
    private final List<RegisteredJobDto> registered;
}
