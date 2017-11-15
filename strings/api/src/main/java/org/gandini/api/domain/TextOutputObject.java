package org.gandini.api.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextOutputObject {

    public TextOutputObject(String texto) {
        this.texto = texto;
    }

    @JsonProperty("text")
    public String texto;
}

