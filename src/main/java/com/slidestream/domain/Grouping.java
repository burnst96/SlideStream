package com.slidestream.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = Grouping.TABLE_NAME)
public class Grouping extends GenericDomain {

    static final String TABLE_NAME = "GROUPING";

    private String name;
    private int currentImageIndex;
    private List<Image> images;
    private Configuration configuration;

    public Grouping() {
        this.currentImageIndex = 0;
        this.configuration = new Configuration(this);
    }

    public Grouping(long totalGroupingCount) {
        this();
        this.name = "Group #" + ++totalGroupingCount;
    }

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(targetEntity = Image.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    public List<Image> getImages() {
        return images;
    }

    @Column(name = "IMAGE_INDEX")
    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public void setCurrentImageIndex(int currentImageIndex) {
        if(images == null) {
            images = new ArrayList<>();
        }

        if(!images.isEmpty()) {
            if(currentImageIndex >= images.size()) {
                this.currentImageIndex = images.size() - 1;
            } else {
                this.currentImageIndex = currentImageIndex;
            }
        } else {
            this.currentImageIndex = 0;
        }
    }

    protected void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        images.add(image);
    }

    public void removeImage(Image image) {
        images.remove(image);
    }

    public void removeImageByIndex(int index) {
        images.remove(index);
    }

    public void removeImageByImagePk(Long imagePk) {
        images = images.stream().filter(i -> i.getPk() != imagePk).collect(Collectors.toList());
    }

    @OneToOne(targetEntity = Configuration.class, cascade = CascadeType.ALL)
    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Transient
    public Image getNextImage() {
        if(!images.isEmpty() && currentImageIndex < images.size() && currentImageIndex > -1) {
            return images.get(currentImageIndex);
        } else {
            return null;
        }
    }
}
