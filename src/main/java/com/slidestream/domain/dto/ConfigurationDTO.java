package com.slidestream.domain.dto;

import com.slidestream.domain.GenericDomain;

public class ConfigurationDTO extends GenericDTO {

    private GroupingDTO group;
    private int imageCycleDelay;
    private String imageCycleAnimation;

    public ConfigurationDTO(GenericDomain gd) {
        super(gd);
    }

    public GroupingDTO getGroup() {
        return group;
    }

    public void setGroup(GroupingDTO group) {
        this.group = group;
    }

    public int getImageCycleDelay() {
        return imageCycleDelay;
    }

    public void setImageCycleDelay(int imageCycleDelay) {
        this.imageCycleDelay = imageCycleDelay;
    }

    public String getImageCycleAnimation() {
        return imageCycleAnimation;
    }

    public void setImageCycleAnimation(String imageCycleAnimation) {
        this.imageCycleAnimation = imageCycleAnimation;
    }
}
