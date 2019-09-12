package com.slidestream.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = Configuration.TABLE_NAME)
public class Configuration extends GenericDomain {

    static final String TABLE_NAME = "CONFIGURATION";

    private Grouping group;
    private int imageCycleDelay;
    private String imageCycleAnimation;

    public Configuration() {
        this.imageCycleDelay = 5;
        this.imageCycleAnimation = "slide";
    }

    public Configuration(Grouping group) {
        this();
        this.group = group;
    }

    @OneToOne(mappedBy = "configuration", cascade = CascadeType.DETACH)
    public Grouping getGroup() {
        return group;
    }

    public void setGroup(Grouping group) {
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
