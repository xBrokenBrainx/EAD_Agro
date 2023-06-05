package com.brokenbrain.agro.gpt.model;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.terreno.model.Terreno;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_Resposta")
public class RespostaGPT {

    @Id
    @GeneratedValue(generator = "SQ_RESPOSTA", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_RESPOSTA", sequenceName = "SQ_RESPOSTA")
    @Column(name = "ID_RESPOSTA")
    private Long idGpt;

    @Column(name = "NM_PLANTA")
    private String nomePlanta;

    @Column(name = "DS_JUSTIFICAITVA")
    private String justificativa;

    @Column(name = "DS_TEMPO")
    private String tempo;

    @Column(name = "DS_COLHEITA")
    private String colheita;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_TERRENO",
            referencedColumnName = "ID_TERRENO",
            foreignKey = @ForeignKey(name = "FK_RESPOSTA_TERRENO", value = ConstraintMode.CONSTRAINT)
    )
    private Terreno terreno;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_AGRICULTOR",
            referencedColumnName = "ID_AGRICULTOR",
            foreignKey = @ForeignKey(name = "FK_RESPOSTA_AGRICULTOR", value = ConstraintMode.CONSTRAINT)
    )
    private Agricultor agricultor;


    public RespostaGPT(){}

    public void setNomePlanta(String nomePlanta) {
        this.nomePlanta = nomePlanta;
    }
    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
    public void setColheita(String colheita) {
        this.colheita = colheita;
    }
    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }
    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }


    public Long getIdGpt() {
        return idGpt;
    }

    public String getNomePlanta() {
        return nomePlanta;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public String getTempo() {
        return tempo;
    }

    public String getColheita() {
        return colheita;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }
}
