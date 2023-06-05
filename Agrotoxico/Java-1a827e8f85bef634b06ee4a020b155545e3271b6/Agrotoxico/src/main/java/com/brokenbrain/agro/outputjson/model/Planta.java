package com.brokenbrain.agro.outputjson.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Planta {

    @JsonProperty("Justificativa")
    private String Justificativa;
    @JsonProperty("Tempo")
    private String Tempo;
    @JsonProperty("Colheita")
    private String Colheita;

    public Planta(){}
    public String getJustificativa() {
        return Justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.Justificativa = justificativa;
    }

    public String getTempo() {
        return Tempo;
    }

    public void setTempo(String tempo) {
        this.Tempo = tempo;
    }

    public String getColheita() {
        return Colheita;
    }

    public void setColheita(String colheita) {
        this.Colheita = colheita;
    }
}
