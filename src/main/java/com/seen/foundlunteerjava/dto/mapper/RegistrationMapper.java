package com.seen.foundlunteerjava.dto.mapper;

import com.seen.foundlunteerjava.dto.response.RegisteredJobDto;
import com.seen.foundlunteerjava.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {JobMapper.class})
public interface RegistrationMapper {
    RegisteredJobDto toDto(Registration registration);
}
