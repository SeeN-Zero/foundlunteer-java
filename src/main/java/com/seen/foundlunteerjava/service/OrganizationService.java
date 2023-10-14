package com.seen.foundlunteerjava.service;

import com.seen.foundlunteerjava.dto.mapper.JobMapper;
import com.seen.foundlunteerjava.dto.mapper.OrganizationMapper;
import com.seen.foundlunteerjava.dto.request.CreateJobRequest;
import com.seen.foundlunteerjava.dto.request.OrganizationUpdateRequest;
import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.model.Job;
import com.seen.foundlunteerjava.model.Organization;
import com.seen.foundlunteerjava.model.UserApp;
import com.seen.foundlunteerjava.model.enums.JobStatus;
import com.seen.foundlunteerjava.repository.JobRepository;
import com.seen.foundlunteerjava.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final OrganizationMapper organizationMapper;
    private final OrganizationRepository organizationRepository;

    public MessageResponse updateOrganization(OrganizationUpdateRequest request) {
        UserApp userDetails = (UserApp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Organization organization = organizationRepository.findOrganizationById(userDetails.getId());
        organizationMapper.partialUpdate(request, organization);
        organizationRepository.save(organization);
        return MessageResponse.builder().message("Success").build();
    }

    public MessageResponse createJob(CreateJobRequest request) {
        UserApp userDetails = (UserApp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Job job = jobMapper.toEntity(request);
        job.setOrganization((Organization) userDetails);
        job.setJobStatus(JobStatus.OPEN);
        jobRepository.save(job);
        return MessageResponse.builder().message("Success").build();
    }
}
