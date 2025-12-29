package com.restaurant.restaurant.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, updatable = false)
    protected LocalDateTime created;

    @Column(nullable = false)
    protected LocalDateTime lastUpdated;

    @Version
    protected long version;

    @PrePersist
    protected void prePersist() {
        this.created = LocalDateTime.now();
        this.lastUpdated = this.created;
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastUpdated = LocalDateTime.now();
    }
}
