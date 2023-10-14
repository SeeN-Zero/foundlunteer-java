package com.seen.foundlunteerjava.dto.mapper;

import com.seen.foundlunteerjava.dto.request.CreateJobRequest;
import com.seen.foundlunteerjava.dto.response.JobDto;
import com.seen.foundlunteerjava.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {OrganizationMapper.class})
public interface JobMapper {
    Job toEntity(CreateJobRequest createJobRequest);

    @Mapping(source = "job.organization", target = "organization")
    @Mapping(source = "job.organization.id", target = "organizationId")
    JobDto toDto(Job job);

}
