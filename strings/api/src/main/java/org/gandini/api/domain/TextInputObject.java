package org.gandini.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextInputObject {

    @JsonProperty("texto")
    public String text;

    @JsonProperty("tamanho_linha")
    public int lineLength;

    @JsonProperty("justificado")
    public boolean justify;

    @JsonProperty("elasticidade")
    public Optional<Double> elasticity =  Optional.of(0.8);//default 0.8

    public TextInputObject() {
    }

    public TextInputObject(String text, int lineLength, boolean justify) {
        this(text, lineLength, justify, Optional.of(0.8));
    }

    public TextInputObject(String text, int lineLength, boolean justify, Optional<Double> elasticity) {
        this.text = text;
        this.lineLength = lineLength;
        this.justify = justify;
        this.elasticity = elasticity;
    }
}
