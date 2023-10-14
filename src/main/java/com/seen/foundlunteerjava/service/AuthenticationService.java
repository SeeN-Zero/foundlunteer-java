package com.seen.foundlunteerjava.service;

import com.seen.foundlunteerjava.dto.mapper.IndividualMapper;
import com.seen.foundlunteerjava.dto.mapper.OrganizationMapper;
import com.seen.foundlunteerjava.dto.request.LoginRequest;
import com.seen.foundlunteerjava.dto.request.RegisterRequest;
import com.seen.foundlunteerjava.dto.response.JwtResponse;
import com.seen.foundlunteerjava.dto.response.MessageResponse;
import com.seen.foundlunteerjava.error.exception.BadPayloadException;
import com.seen.foundlunteerjava.model.enums.Role;
import com.seen.foundlunteerjava.repository.IndividualRepository;
import com.seen.foundlunteerjava.repository.OrganizationRepository;
import com.seen.foundlunteerjava.repository.UserAppRepository;
import com.seen.foundlunteerjava.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserAppRepository userAppRepository;
    private final AuthenticationManager authenticationManager;
    private final IndividualRepository individualRepository;
    private final OrganizationRepository organizationRepository;
    private final IndividualMapper individualMapper;
    private final OrganizationMapper organizationMapper;

    public MessageResponse register(RegisterRequest request) throws BadPayloadException {
        if (userAppRepository.existsByEmailIgnoreCase(request.getEmail())) {
            throw new BadPayloadException("Email Already Use");
        }
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        request.setRole(request.getRole().toUpperCase());
        Role role = Role.valueOf(request.getRole());
        if (role.equals(Role.INDIVIDUAL)) {
            individualRepository.save(individualMapper.toEntity(request));
        } else {
            organizationRepository.save(organizationMapper.toEntity(request));
        }
        return MessageResponse.builder().message("Success").build();
    }

    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userAppRepository.findByEmailIgnoreCase(request.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtResponse.builder().token(jwt).build();
    }
}
