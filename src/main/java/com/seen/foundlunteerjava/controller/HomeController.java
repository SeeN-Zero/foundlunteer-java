package com.seen.foundlunteerjava.controller;

import com.seen.foundlunteerjava.dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<MessageResponse> helloWorld() {
        return ResponseEntity.ok(MessageResponse.builder().message("Hello Foundlunteer").build());
    }
}
