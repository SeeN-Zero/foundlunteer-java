package com.seen.foundlunteerjava.model.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationKey implements Serializable {
    @Column(name = "individual_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID individualId;

    @Column(name = "job_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID jobId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RegistrationKey entity = (RegistrationKey) o;
        return Objects.equals(this.jobId, entity.jobId) &&
                Objects.equals(this.individualId, entity.individualId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jobId, individualId);
    }
}

