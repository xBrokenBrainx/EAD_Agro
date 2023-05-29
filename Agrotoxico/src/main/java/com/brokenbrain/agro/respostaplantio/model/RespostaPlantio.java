package com.brokenbrain.agro.respostaplantio.model;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import jakarta.persistence.*;


@Entity
@Table(name = "TB_Resposta_Plantio")
public class RespostaPlantio {

    @Id
    @GeneratedValue(generator = "SQ_RESPOSTA", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SQ_RESPOSTA", sequenceName = "SQ_RESPOSTA")
    @Column(name = "ID_RESPOSTA")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_AGRICULTOR",
            referencedColumnName = "ID_AGRICULTOR",
            foreignKey = @ForeignKey(name = "FK_RESPOSTA_AGRICULTOR", value = ConstraintMode.CONSTRAINT)
    )
    private Agricultor agricultor;

    @Column(name = "NM_ESTACAO")
    private String nmEstacao;

    @Column(name = "DT_COLHEITA")
    private int dtColheita;

    @Column(name = "QTD_OPCOES_PLANTAS")
    private int qtdOpcoes;

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public void setNmEstacao(String nmEstacao) {
        this.nmEstacao = nmEstacao;
    }

    public void setDtColheita(int dtColheita) {
        this.dtColheita = dtColheita;
    }

    public void setQtdOpcoes(int qtdOpcoes) {
        this.qtdOpcoes = qtdOpcoes;
    }

    public Long getId() {
        return id;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public String getNmEstacao() {
        return nmEstacao;
    }

    public int getDtColheita() {
        return dtColheita;
    }

    public int getQtdOpcoes() {
        return qtdOpcoes;
    }
}
