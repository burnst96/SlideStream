package com.slidestream.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;

@MappedSuperclass
@XmlTransient
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@pk")
public abstract class GenericDomain {

    private long pk;
    private LocalDateTime lastModified;

    protected GenericDomain() {
        lastModified = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="PK", nullable=false)
    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }

    @Column(name="LAST_UPDATED")
    @Type(type = "java.time.LocalDateTime")
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}
