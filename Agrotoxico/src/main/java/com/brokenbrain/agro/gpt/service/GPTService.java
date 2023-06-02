package com.brokenbrain.agro.gpt.service;

import com.brokenbrain.agro.gpt.model.RespostaGPT;
import com.brokenbrain.agro.outputjson.model.JsonPlantas;
import com.brokenbrain.agro.terreno.model.Terreno;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class GPTService {

    private static String KEY = "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";
    private String PROMPT;
    private long MAX_TOKENS = 1500;
    private float TEMPERATURE = 1;
    private String MODEL = "text-davinci-003";
    private Map<String, Object> jsonInput = new HashMap<>();
    private String outputGpt;
    private String respostaJson;
    private Map<String, Object> jsonOutput;
    private RespostaGPT respostaGPT;
    private Terreno terreno;

    public GPTService(){}

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
    public void stringParaJson(String stringJson) throws JsonProcessingException {
        String stringJsonTratada = stringJson
                .substring( stringJson.indexOf("{"),
                            stringJson.lastIndexOf("}") + 1 );
        setPlantaResposta(stringJsonTratada);
    }

    public void gerarRespostaPlantio(String prompt) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost
                    ("https://api.openai.com/v1/completions");

            jsonInput.put("model", MODEL);
            jsonInput.put("prompt", this.getPROMPT());
            jsonInput.put("max_tokens", MAX_TOKENS);
            jsonInput.put("temperature", TEMPERATURE);
            ObjectMapper jsonInputMapper = new ObjectMapper();
            String jsonInputData = jsonInputMapper.writeValueAsString(jsonInput);
            StringEntity inputGptEntity = new StringEntity(jsonInputData);

            inputGptEntity.setContentType("application/json");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + KEY);
            post.setEntity(inputGptEntity);
            HttpResponse response = client.execute(post);
            if (response.getStatusLine().getStatusCode() != 201)
                System.out.println("HTTP Status Code: " + response.getStatusLine().getStatusCode());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader( response.getEntity().getContent() ));

            ObjectMapper jsonOutputMapper = new ObjectMapper();

            System.out.println("\nGPT Resposta: \n");
            while ( ( outputGpt = reader.readLine() ) != null) {
                jsonOutput = jsonOutputMapper.readValue(outputGpt, Map.class);
            }

            String choicesJson = (String) ( (Map) ( (List)
                    jsonOutput.get("choices") )
                    .get(0) )
                    .get("text");

            System.out.println("\n\n\nChoices: "+jsonOutput.get("choices"));
            stringParaJson(choicesJson);

            client.getConnectionManager().shutdown();

        } catch (Exception exception) {
            System.out.println("\nErro ao gerar resposta de plantio: " + exception.getMessage());
        }
    }

    public String gerarPrompt(Terreno terreno){
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
        return tratarString(prompt);
    }

    private String tratarString(String prompt){
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

    public void setRespostaGPT(RespostaGPT respostaGPT) {
        this.respostaGPT = respostaGPT;
    }
    public void setPROMPT(String PROMPT) {
        this.PROMPT = PROMPT;
    }

    public String getPROMPT() {
        return PROMPT;
    }


}