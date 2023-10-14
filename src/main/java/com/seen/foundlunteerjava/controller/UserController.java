package com.seen.foundlunteerjava.controller;

import com.seen.foundlunteerjava.dto.response.JobDto;
import com.seen.foundlunteerjava.dto.response.JobsDto;
import com.seen.foundlunteerjava.error.exception.UnknownException;
import com.seen.foundlunteerjava.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class UserController {

    private final UserService userService;

    @GetMapping("/user/get")
    public ResponseEntity<Object> getUser() throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }

    @GetMapping("/user/get-all-job")
    public ResponseEntity<JobsDto> getAllJob() throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllJob());
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }

    @GetMapping("/user/get-job/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable(name = "id") String jobId) throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getJob(jobId));
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }
}
