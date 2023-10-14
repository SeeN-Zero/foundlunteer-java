package com.seen.foundlunteerjava.dto.response;

import com.seen.foundlunteerjava.model.enums.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class JobDto {
    private final UUID id;
    private final String title;
    private final String description;
    private final String location;
    private final JobStatus jobStatus;
    private final UUID organizationId;
    private final LocalDate createdAt;
    private final LocalDate expiredAt;
    private final OrganizationDto organization;
    private final String image;
}
