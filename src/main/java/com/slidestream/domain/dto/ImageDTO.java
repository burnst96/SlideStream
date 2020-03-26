package com.slidestream.domain.dto;

import com.slidestream.domain.GenericDomain;

import java.util.HashSet;
import java.util.Set;

public class ImageDTO extends GenericDTO {

    private String name;
    private byte[] value;
    private Set<GroupingDTO> groupings = new HashSet<>();

    public ImageDTO(GenericDomain gd) {
        super(gd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getValue() {
        return value;
    }

    public void setValue(byte[] value) {
        this.value = value;
    }

    public Set<GroupingDTO> getGroupings() {
        return groupings;
    }

    public void setGroupings(Set<GroupingDTO> groupings) {
        this.groupings = groupings;
    }
}
