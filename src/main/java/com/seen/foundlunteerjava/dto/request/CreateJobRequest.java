package com.seen.foundlunteerjava.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateJobRequest {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotBlank(message = "Location cannot be empty")
    private String location;
    @NotBlank(message = "Expire Date cannot be empty")
    private String expiredAt;
}
