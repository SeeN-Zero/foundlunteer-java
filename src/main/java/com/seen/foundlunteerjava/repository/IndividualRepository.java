package com.seen.foundlunteerjava.repository;

import com.seen.foundlunteerjava.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.UUID;

public interface IndividualRepository extends JpaRepository<Individual, UUID>, JpaSpecificationExecutor<Individual> {
    Individual findIndividualById(@NonNull UUID id);

}
