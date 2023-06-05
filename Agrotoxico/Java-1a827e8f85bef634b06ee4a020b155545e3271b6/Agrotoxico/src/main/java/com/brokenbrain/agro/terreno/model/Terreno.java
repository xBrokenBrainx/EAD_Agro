package com.brokenbrain.agro.terreno.model;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import jakarta.persistence.*;


@Entity
@Table(name = "TB_Terreno")
public class Terreno {

    @Id
    @GeneratedValue(generator = "SQ_TERRENO", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "SQ_TERRENO", sequenceName = "SQ_TERRENO")
    @Column(name = "ID_TERRENO")
    private Long id;

    @Column(name = "QTD_ESPACO")
    private Float qtdEspaco;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_AGRICULTOR",
            referencedColumnName = "ID_AGRICULTOR",
            foreignKey = @ForeignKey(name = "FK_TERRENO_AGRICULTOR", value = ConstraintMode.CONSTRAINT)
    )
    private Agricultor agricultor;

    @Column(name = "NM_ESTACAO")
    private String nmEstacao;

    public Terreno(Agricultor agricultor){
        setAgricultor(agricultor);
    }
    public Terreno(){}


    public Float getQtdEspaco() {
        return qtdEspaco;
    }

    public void setQtdEspaco(Float qtdEspaco) {
        this.qtdEspaco = qtdEspaco;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public void setNmEstacao(String nmEstacao) {
        this.nmEstacao = nmEstacao;
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


}
