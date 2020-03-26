package com.slidestream.domain.dto;

import com.slidestream.domain.GenericDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

public abstract class GenericDTO {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private long pk;

    public GenericDTO(GenericDomain gd) {
        Field[] gdFields = gd.getClass().getDeclaredFields();
        Field[] dtoFields = this.getClass().getDeclaredFields();

        pk = gd.getPk();

        for (Field gdField : gdFields) {
            gdField.setAccessible(true);
            for (Field dtoField : dtoFields) {
                dtoField.setAccessible(true);
                if (gdField.getName().equalsIgnoreCase(dtoField.getName()) && gdField.getType().equals(dtoField.getType()) && gdField.getType() != List.class && gdField.getType() != Set.class) {
                    try {
                        dtoField.set(this, gdField.get(gd));
                    } catch (Exception e) {
                        LOGGER.error("Could not convert Image to ImageDTO", e);
                    }
                }
            }
        }
    }

    public long getPk() {
        return pk;
    }

    public void setPk(long pk) {
        this.pk = pk;
    }
}
