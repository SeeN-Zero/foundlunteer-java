package com.seen.foundlunteerjava.controller;

import com.seen.foundlunteerjava.dto.request.CreateJobRequest;
import com.seen.foundlunteerjava.dto.request.OrganizationUpdateRequest;
import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.error.exception.UnknownException;
import com.seen.foundlunteerjava.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/organization/update")
    @PreAuthorize("hasAuthority('ORGANIZATION')")
    public ResponseEntity<MessageResponse> updateOrganization(@RequestBody OrganizationUpdateRequest request) throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(organizationService.updateOrganization(request));
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }

    @PostMapping("/organization/add-job")
    @PreAuthorize("hasAuthority('ORGANIZATION')")
    public ResponseEntity<MessageResponse> createJob(@Valid @RequestBody CreateJobRequest request) throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(organizationService.createJob(request));
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }
}
