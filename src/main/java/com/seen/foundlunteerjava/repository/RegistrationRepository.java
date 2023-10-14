package com.seen.foundlunteerjava.repository;

import com.seen.foundlunteerjava.model.Registration;
import com.seen.foundlunteerjava.model.key.RegistrationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

public interface RegistrationRepository extends JpaRepository<Registration, RegistrationKey>, JpaSpecificationExecutor<Registration> {
    boolean existsByRegistrationKey(@NonNull RegistrationKey registrationKey);
}
