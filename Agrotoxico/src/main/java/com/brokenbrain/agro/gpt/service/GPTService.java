package com.brokenbrain.agro.gpt.service;

import com.brokenbrain.agro.gpt.model.GPT;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@Data
public class GPTService extends GPT {

    public GPTService(){}

    private DefaultHttpClient httpPostGpt(StringEntity entity) throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();

        HttpPost post = new HttpPost
                ("https://api.openai.com/v1/completions");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", "Bearer " + KEY);
        post.setEntity(entity);

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
        return client;
    }
    public void gerarRespostaPlantio() {
        try {
            var inputGptEntity = gerarGptEntity();
            var client = httpPostGpt(inputGptEntity);

            String choicesJson = (String)
                    ( (Map) ( (List)
                    jsonOutput.get("choices") )
                    .get(0) )
                    .get("text");

            System.out.println("\n\n\nChoices: "+jsonOutput.get("choices"));
            stringParaJson(choicesJson);

            client.getConnectionManager().shutdown();
        } catch (Exception exception) {
            System.out.println("\nErro ao gerar resposta de plantio: " +
                    exception.getMessage() );
        }
    }

}