package com.seen.foundlunteerjava.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Individual extends UserApp {

    @Column(name = "birth_of_date")
    private LocalDate birthOfDate;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "social")
    private String social;

    @OneToMany(mappedBy = "individual", orphanRemoval = true)
    private List<Registration> registeredJobs = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Individual that = (Individual) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

}
