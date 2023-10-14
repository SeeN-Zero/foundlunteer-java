package com.seen.foundlunteerjava.dto.mapper;

import com.seen.foundlunteerjava.dto.request.OrganizationUpdateRequest;
import com.seen.foundlunteerjava.dto.request.RegisterRequest;
import com.seen.foundlunteerjava.dto.response.OrganizationDto;
import com.seen.foundlunteerjava.model.Organization;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {
    Organization toEntity(RegisterRequest registerRequest);
    Organization toEntity(OrganizationUpdateRequest organizationUpdateRequest);

    @Mapping(source = "organization", target = "user")
    OrganizationDto toDto(Organization organization);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(OrganizationUpdateRequest organizationUpdateRequest, @MappingTarget Organization organization);

}
