package com.brokenbrain.agro.gpt.model;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.respostaplantio.model.RespostaPlantio;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_GPT")
public class GPTAgro {

    @Id
    @GeneratedValue(generator = "SQ_GPT", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_GPT", sequenceName = "SQ_GPT")
    @Column(name = "ID_GPT")
    private Long idGpt;

    @Column(name = "MSG_INPUT_PROMPT")
    private String msgInput;

    @Column(name = "MSG_OUTPUT_PLANTIO")
    private String msgOutput;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_RESPOSTA",
            referencedColumnName = "ID_RESPOSTA",
            foreignKey = @ForeignKey(name = "FK_GPT_RESPOSTA", value = ConstraintMode.CONSTRAINT)
    )
    private RespostaPlantio respostaPlantio;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "ID_AGRICULTOR",
            referencedColumnName = "ID_AGRICULTOR",
            foreignKey = @ForeignKey(name = "FK_GPT_AGRICULTOR", value = ConstraintMode.CONSTRAINT)
    )
    private Agricultor agricultor;

    public void setRespostaPlantio(RespostaPlantio respostaPlantio) {
        this.respostaPlantio = respostaPlantio;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public Long getIdGpt() {
        return idGpt;
    }

    public String getMsgInput() {
        return msgInput;
    }

    public String getMsgOutput() {
        return msgOutput;
    }

    public RespostaPlantio getRespostaPlantio() {
        return respostaPlantio;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }
}
