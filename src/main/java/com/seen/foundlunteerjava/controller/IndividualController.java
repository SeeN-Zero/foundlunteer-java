package com.seen.foundlunteerjava.controller;

import com.seen.foundlunteerjava.dto.request.IndividualUpdateRequest;
import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.dto.response.RegisteredJobsDto;
import com.seen.foundlunteerjava.error.exception.BadPayloadException;
import com.seen.foundlunteerjava.error.exception.DataNotFoundException;
import com.seen.foundlunteerjava.error.exception.UnknownException;
import com.seen.foundlunteerjava.model.Registration;
import com.seen.foundlunteerjava.service.IndividualService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Log4j2
public class IndividualController {

    private final IndividualService individualService;

    @PostMapping("/individual/update")
    @PreAuthorize("hasAuthority('INDIVIDUAL')")
    public ResponseEntity<MessageResponse> updateIndividual(@RequestBody IndividualUpdateRequest request) throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(individualService.updateIndividual(request));
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }

    @PostMapping("/individual/register/{id}")
    @PreAuthorize("hasAuthority('INDIVIDUAL')")
    public ResponseEntity<MessageResponse> registerIndividual(@PathVariable(name = "id") String jobId) throws UnknownException, DataNotFoundException, BadPayloadException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(individualService.registerIndividual(jobId));
        }catch (DataNotFoundException | BadPayloadException e){
            log.error("Exception : {}", e.toString());
            throw e;
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }
    @GetMapping("/individual/registered-job")
    @PreAuthorize("hasAuthority('INDIVIDUAL')")
    public ResponseEntity<RegisteredJobsDto> getRegisteredJob() throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(individualService.getRegisteredJob());
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }



}
