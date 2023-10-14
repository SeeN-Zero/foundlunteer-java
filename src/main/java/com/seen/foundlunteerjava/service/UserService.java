package com.seen.foundlunteerjava.service;

import com.seen.foundlunteerjava.dto.mapper.IndividualMapper;
import com.seen.foundlunteerjava.dto.mapper.JobMapper;
import com.seen.foundlunteerjava.dto.mapper.OrganizationMapper;
import com.seen.foundlunteerjava.dto.response.JobDto;
import com.seen.foundlunteerjava.dto.response.JobsDto;
import com.seen.foundlunteerjava.error.exception.DataNotFoundException;
import com.seen.foundlunteerjava.model.Individual;
import com.seen.foundlunteerjava.model.Organization;
import com.seen.foundlunteerjava.model.UserApp;
import com.seen.foundlunteerjava.model.enums.Role;
import com.seen.foundlunteerjava.repository.IndividualRepository;
import com.seen.foundlunteerjava.repository.JobRepository;
import com.seen.foundlunteerjava.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IndividualRepository individualRepository;
    private final OrganizationRepository organizationRepository;
    private final IndividualMapper individualMapper;
    private final OrganizationMapper organizationMapper;
    private final JobMapper jobMapper;
    private final JobRepository jobRepository;

    public Object getUser() {
        UserApp userDetails = (UserApp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRole() == Role.INDIVIDUAL) {
            Individual individual = individualRepository.findIndividualById(userDetails.getId());
            return individualMapper.toDto(individual);
        } else {
            Organization organization = organizationRepository.findOrganizationById(userDetails.getId());
            return organizationMapper.toDto(organization);
        }
    }

    public JobsDto getAllJob() {
        return JobsDto.builder().jobs(jobRepository.findAll().stream().map(jobMapper::toDto).toList()).build();
    }

    public JobDto getJob(String jobId) throws DataNotFoundException {
        return jobMapper.toDto(
                jobRepository.findById(UUID.fromString(jobId)).orElseThrow(
                        () -> new DataNotFoundException("Job id not valid, job not found")
                )
        );
    }
}
