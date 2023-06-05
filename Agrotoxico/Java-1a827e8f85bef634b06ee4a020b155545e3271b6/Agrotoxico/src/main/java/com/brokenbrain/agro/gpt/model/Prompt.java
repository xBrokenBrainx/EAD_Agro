package com.brokenbrain.agro.gpt.model;

import com.brokenbrain.agro.terreno.model.Terreno;

public class Prompt {
    private String PROMPT;
    private Terreno terreno;
    public Prompt(){}

    public void gerarPrompt(Terreno terreno){
        setTerreno(terreno);
        String prompt =
                "Você é consultor Brasileiro do agronegócio especialista em plantações de pouco espaço e alto rendimento para pequenos agricultores. "+
                        "Faça uma lista com recomendações de plantio com os seguintes parâmetros: "+
                        "três opções diferentes de plantio, com um terreno de "+
                        terreno.getQtdEspaco() +
                        " metros quadrados, condições favoráveis de clima na cidade "+
                        terreno.getAgricultor().getCidade() +
                        " na estação do ano "+
                        terreno.getNmEstacao() +
                        ". Justifique cada item brevemente."+
                        " Faça uma estimativa de tempo para colher e estimativa de quantidade de colheita."+
                        "A resposta deve vir no formato JSON, como no exemplo: "+
                        "{ \"Plantas\": { \"Tomate\": {\"Justificativa\":\"\", \"Tempo\":\"x-y Semanas\", \"Colheita\":\"x-ykg/m2\" } } }";

        this.setPROMPT(tratarString(prompt));
    }

    protected String tratarString(String prompt){
        return prompt
                .replace('ç', 'c')
                .replace('á', 'a')
                .replace('ã', 'a')
                .replace('â', 'a')
                .replace('à', 'a')
                .replace('é', 'e')
                .replace('è', 'e')
                .replace('ê', 'e')
                .replace('ẽ', 'e')
                .replace('í', 'i')
                .replace('ì', 'i')
                .replace('î', 'i')
                .replace('ĩ', 'i')
                .replace('ô', 'o')
                .replace('ò', 'o')
                .replace('ó', 'o')
                .replace('õ','o')
                .replace('ú', 'u')
                .replace('ũ', 'u')
                .replace('û', 'u')
                .replace('ù', 'u');
    }


    protected void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    protected Terreno getTerreno() {
        return terreno;
    }

    protected String getPROMPT() {
        return PROMPT;
    }

    protected void setPROMPT(String PROMPT) {
        this.PROMPT = PROMPT;
    }
}
