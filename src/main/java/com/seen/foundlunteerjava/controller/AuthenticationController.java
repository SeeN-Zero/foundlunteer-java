package com.seen.foundlunteerjava.controller;


import com.seen.foundlunteerjava.dto.request.LoginRequest;
import com.seen.foundlunteerjava.dto.request.RegisterRequest;
import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.error.exception.BadPayloadException;
import com.seen.foundlunteerjava.error.exception.UnknownException;
import com.seen.foundlunteerjava.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/user/add")
    public ResponseEntity<MessageResponse> signUp(@Valid @RequestBody RegisterRequest request) throws BadPayloadException, UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.register(request));
        } catch (BadPayloadException e) {
            log.error("Exception : {}", e.toString());
            throw e;
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest request) throws UnknownException {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(authenticationService.login(request));
        } catch (Exception e) {
            log.error("Exception : {}", e.toString());
            throw new UnknownException();
        }
    }
}
