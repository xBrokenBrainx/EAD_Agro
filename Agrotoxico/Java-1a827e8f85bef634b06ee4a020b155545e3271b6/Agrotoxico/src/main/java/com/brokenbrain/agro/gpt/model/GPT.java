package com.brokenbrain.agro.gpt.model;

import com.brokenbrain.agro.outputjson.model.JsonPlantas;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class GPT extends Prompt{
    protected static String KEY = "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";
    protected long MAX_TOKENS = 1500;
    protected float TEMPERATURE = 1;
    protected String MODEL = "text-davinci-003";
    protected Map<String, Object> jsonInput = new HashMap<>();
    protected String outputGpt;
    private String respostaJson;
    protected Map<String, Object> jsonOutput;
    protected RespostaGPT respostaGPT;

    private void setPlantaResposta(String stringPlanta) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        JsonPlantas jsonPlanta;
        jsonPlanta = mapper.readValue(stringPlanta, JsonPlantas.class);

        jsonPlanta.getPlantas()
                .forEach(
                        (planta, plantas) ->
                        {
                            this.respostaGPT.setNomePlanta( planta );
                            this.respostaGPT.setJustificativa(
                                    jsonPlanta.getPlantas()
                                            .get(planta)
                                            .getJustificativa()
                            );
                            this.respostaGPT.setTempo(
                                    jsonPlanta.getPlantas()
                                            .get(planta)
                                            .getTempo()
                            );
                            this.respostaGPT.setColheita(
                                    jsonPlanta.getPlantas()
                                            .get(planta)
                                            .getColheita()
                            );
                        }
                );
    }
    protected void stringParaJson(String stringJson)
            throws JsonProcessingException {
        String stringJsonTratada = stringJson
                .substring( stringJson.indexOf("{"),
                        stringJson.lastIndexOf("}") + 1 );
        setPlantaResposta(stringJsonTratada);
    }

    protected StringEntity gerarGptEntity()
            throws JsonProcessingException, UnsupportedEncodingException {
        jsonInput.put("model", MODEL);
        jsonInput.put("prompt", this.getPROMPT());
        jsonInput.put("max_tokens", MAX_TOKENS);
        jsonInput.put("temperature", TEMPERATURE);
        ObjectMapper jsonInputMapper = new ObjectMapper();
        String jsonInputData = jsonInputMapper.writeValueAsString(jsonInput);
        StringEntity inputGptEntity = new StringEntity(jsonInputData);
        inputGptEntity.setContentType("application/json");
        return inputGptEntity;
    }


    public void setRespostaGPT(RespostaGPT respostaGPT) {
        this.respostaGPT = respostaGPT;
    }
}
