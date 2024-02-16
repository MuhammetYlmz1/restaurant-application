package com.muhammet.restaurantapplication.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

    @PrePersist
    public void prePersist(){
        if (this.createdAt == null){
            this.createdAt = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate(){
        this.modifiedAt =LocalDateTime.now();
    }

}
