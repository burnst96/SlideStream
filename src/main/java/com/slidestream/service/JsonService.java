package com.slidestream.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class JsonService {

    private static Logger LOGGER = LoggerFactory.getLogger(JsonService.class);

    @Resource
    private ObjectMapper objectMapper;

    @Transactional
    public String convertToJsonString(Object entity) {
        if(entity == null) {
            return "{}";
        } else {
            try {
                return objectMapper.writeValueAsString(entity);
            } catch(JsonProcessingException e) {
                LOGGER.error("Groupings list could not be serialized to Json String.", e);
                return "{}";
            }
        }
    }

}
