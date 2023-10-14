package com.seen.foundlunteerjava.model;

import com.seen.foundlunteerjava.model.enums.RegistrationStatus;
import com.seen.foundlunteerjava.model.key.RegistrationKey;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "registration")
public class Registration {
    @EmbeddedId
    private RegistrationKey registrationKey;

    @ManyToOne
    @MapsId("individualId")
    @JoinColumn(name = "individual_id")
    private Individual individual;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status", nullable = false)
    private RegistrationStatus registrationStatus;

    @Column(name = "register_at",
            nullable = false,
            insertable = false,
            updatable = false,
            columnDefinition = "TIMESTAMP(0) DEFAULT CURRENT_TIMESTAMP")
    private LocalDate registerAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Registration that = (Registration) o;
        return getRegistrationKey() != null && Objects.equals(getRegistrationKey(), that.getRegistrationKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationKey);
    }
}
