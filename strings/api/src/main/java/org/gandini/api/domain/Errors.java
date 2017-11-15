package org.gandini.api.domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Errors {

    public List<String> erros = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String toString() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            try {
                return objectMapper.writeValueAsString(new Errors());
            } catch (JsonProcessingException e1) {
            }
        }
        return "";
    }
}