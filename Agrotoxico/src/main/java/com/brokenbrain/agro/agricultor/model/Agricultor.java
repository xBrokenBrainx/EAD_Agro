package com.brokenbrain.agro.agricultor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_Agricultor")
public class Agricultor {

    @Id
    @GeneratedValue(generator = "SQ_AGRICULTOR", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_AGRICULTOR", sequenceName = "SQ_AGRICULTOR")
    @Column(name = "ID_AGRICULTOR")
    private Long id;

    @Column(name = "QTD_ESPACO")
    private Float qtdEspaco;

    @Column(name = "NM_CIDADE")
    private String cidade;

    public void setQtdEspaco(Float qtdEspaco) {
        this.qtdEspaco = qtdEspaco;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public Float getQtdEspaco() {
        return qtdEspaco;
    }

    public String getCidade() {
        return cidade;
    }

}
