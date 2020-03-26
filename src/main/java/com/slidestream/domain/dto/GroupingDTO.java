package com.slidestream.domain.dto;

import com.slidestream.domain.GenericDomain;

import java.util.List;

public class GroupingDTO extends GenericDTO {

    private String name;
    private int currentImageIndex;
    private List<ImageDTO> images;
    private ConfigurationDTO configuration;

    public GroupingDTO(GenericDomain gd) {
        super(gd);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public void setCurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public ConfigurationDTO getConfiguration() {
        return configuration;
    }

    public void setConfiguration(ConfigurationDTO configuration) {
        this.configuration = configuration;
    }
}
