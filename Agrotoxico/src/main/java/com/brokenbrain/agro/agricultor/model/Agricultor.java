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
    @Column(name = "NM_CIDADE")
    private String cidade;

    @Column(name = "NM_USERNAME")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }


}
