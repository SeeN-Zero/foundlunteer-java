package com.seen.foundlunteerjava.dto.response;

import com.seen.foundlunteerjava.model.enums.JobStatus;
import com.seen.foundlunteerjava.model.enums.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisteredJobDto {
    private final RegistrationStatus registrationStatus;
    private final JobDto job;
}
