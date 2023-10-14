package com.seen.foundlunteerjava.service;

import com.seen.foundlunteerjava.dto.mapper.IndividualMapper;
import com.seen.foundlunteerjava.dto.mapper.RegistrationMapper;
import com.seen.foundlunteerjava.dto.request.IndividualUpdateRequest;
import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.dto.response.RegisteredJobsDto;
import com.seen.foundlunteerjava.error.exception.BadPayloadException;
import com.seen.foundlunteerjava.error.exception.DataNotFoundException;
import com.seen.foundlunteerjava.model.Individual;
import com.seen.foundlunteerjava.model.Job;
import com.seen.foundlunteerjava.model.Registration;
import com.seen.foundlunteerjava.model.UserApp;
import com.seen.foundlunteerjava.model.enums.JobStatus;
import com.seen.foundlunteerjava.model.enums.RegistrationStatus;
import com.seen.foundlunteerjava.model.key.RegistrationKey;
import com.seen.foundlunteerjava.repository.IndividualRepository;
import com.seen.foundlunteerjava.repository.JobRepository;
import com.seen.foundlunteerjava.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IndividualService {

    private final IndividualRepository individualRepository;
    private final IndividualMapper individualMapper;
    private final RegistrationMapper registrationMapper;
    private final JobRepository jobRepository;
    private final RegistrationRepository registrationRepository;

    public MessageResponse updateIndividual(IndividualUpdateRequest request) {
        UserApp userDetails = (UserApp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Individual individual = individualRepository.findIndividualById(userDetails.getId());
        individualMapper.partialUpdate(request, individual);
        individualRepository.save(individual);
        return MessageResponse.builder().message("Success").build();
    }

    public MessageResponse registerIndividual(String jobId) throws DataNotFoundException, BadPayloadException {
        UserApp userDetails = (UserApp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Job job = jobRepository.findById(UUID.fromString(jobId)).orElseThrow(
                () -> new DataNotFoundException("Job id not valid or job not found")
        );
        if (job.getJobStatus() != JobStatus.OPEN) {
            throw new BadPayloadException("Job already closed");
        }
        Individual individual = individualRepository.findIndividualById(userDetails.getId());
        RegistrationKey registrationKey = RegistrationKey.builder()
                .individualId(individual.getId())
                .jobId(job.getId())
                .build();
        Registration registration = Registration.builder()
                .registrationKey(registrationKey)
                .individual(individual)
                .job(job)
                .registrationStatus(RegistrationStatus.ONPROGRESS)
                .build();
        if (registrationRepository.existsByRegistrationKey(registrationKey)){
            throw new BadPayloadException("Job Already Registered");
        }
        registrationRepository.save(registration);
        return MessageResponse.builder().message("Success").build();
    }

    public RegisteredJobsDto getRegisteredJob() {
        return RegisteredJobsDto.builder().registered(registrationRepository.findAll().stream().map(registrationMapper::toDto).toList()).build();
    }
}
