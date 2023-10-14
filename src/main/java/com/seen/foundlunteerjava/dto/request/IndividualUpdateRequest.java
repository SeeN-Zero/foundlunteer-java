package com.seen.foundlunteerjava.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class IndividualUpdateRequest {
    private String name;
    private String address;
    private String phone;
    private String birthOfDate;
    private String description;
    private String social;
}
