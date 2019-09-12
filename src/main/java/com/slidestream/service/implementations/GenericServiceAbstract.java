package com.slidestream.service.implementations;

import com.slidestream.service.JsonService;
import com.slidestream.service.interfaces.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class GenericServiceAbstract<T> implements GenericService<T> {

    @Autowired
    private JsonService jsonService;

    @Override
    public String getAsJsonString(T entity) {
        return jsonService.convertToJsonString(entity);
    }

    @Override
    public String getAsJsonString(List<T> entities) {
        return jsonService.convertToJsonString(entities);
    }

}
