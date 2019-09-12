package com.slidestream.domain;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Image.TABLE_NAME)
public class Image extends GenericDomain {

    static final String TABLE_NAME = "IMAGE";

    private String name;
    private byte[] value;
    private Set<Grouping> groupings = new HashSet<>();

    public Image() {}

    public Image(String name, byte[] value) {
        super();
        this.name = name;
        this.value = value;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "VALUE", nullable = false, columnDefinition = "IMAGE")
    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    @ManyToMany(mappedBy = "images", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    public Set<Grouping> getGroupings() {
        return groupings;
    }

    public void setGroupings(Set<Grouping> groupings) {
        this.groupings = groupings;
    }

    @Transient
    public String getValueAsBase64String() {
        if(value != null) {
            return Base64.encodeBase64String(value);
        } else {
            return StringUtils.EMPTY;
        }
    }
}
