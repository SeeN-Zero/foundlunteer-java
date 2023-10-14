package com.seen.foundlunteerjava.service;

import com.seen.foundlunteerjava.repository.UserAppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAppDetailsService implements UserDetailsService {

    private final UserAppRepository userAppRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository.findByEmailIgnoreCase(username).orElseThrow(
                () -> new UsernameNotFoundException("Email " + username + " Not Found")
        );
    }
}
