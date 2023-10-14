package com.seen.foundlunteerjava.dto.mapper;

import com.seen.foundlunteerjava.dto.request.IndividualUpdateRequest;
import com.seen.foundlunteerjava.dto.request.RegisterRequest;
import com.seen.foundlunteerjava.dto.response.IndividualDto;
import com.seen.foundlunteerjava.model.Individual;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IndividualMapper {
    Individual toEntity(RegisterRequest registerRequest);

    @Mapping(source = "individual", target = "user")
    IndividualDto toDto(Individual individual);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(IndividualUpdateRequest individualUpdateRequest, @MappingTarget Individual individual);
}
