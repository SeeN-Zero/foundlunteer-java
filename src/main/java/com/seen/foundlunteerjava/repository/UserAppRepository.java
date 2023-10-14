package com.seen.foundlunteerjava.repository;

import com.seen.foundlunteerjava.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.UUID;

public interface UserAppRepository extends JpaRepository<UserApp, UUID>, JpaSpecificationExecutor<UserApp> {
    boolean existsByEmailIgnoreCase(@NonNull String email);

    Optional<UserApp> findByEmailIgnoreCase(@NonNull String email);
}
