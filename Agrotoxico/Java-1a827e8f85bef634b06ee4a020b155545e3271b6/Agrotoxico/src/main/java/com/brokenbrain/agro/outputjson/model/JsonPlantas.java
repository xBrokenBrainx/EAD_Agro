package com.brokenbrain.agro.outputjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class JsonPlantas {

    @JsonProperty("Plantas")
    private Map<String, Planta> plantas;

    public Map<String, Planta> getPlantas() {
        return plantas;
    }

    public void setPlantas(Map<String, Planta> plantas) {
        this.plantas = plantas;
    }
}
