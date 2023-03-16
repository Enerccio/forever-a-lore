package com.en_circle.fas.model.entities;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class JPAEntity {

    @Id
    @GeneratedValue
    protected Long id;

    @Column(length = 64, unique = true)
    private String uuid;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getCreation() {
        return creation;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }
}
