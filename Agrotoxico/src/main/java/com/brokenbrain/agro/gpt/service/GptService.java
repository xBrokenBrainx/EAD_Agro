package com.brokenbrain.agro.gpt.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;


@Data
public class GptService {

    // - Setando parâmetros de entrada para o input na API da Open AI -
    private static String KEY = "";
    private String PROMPT;
    private long MAX_TOKENS = 1000;
    private float TEMPERATURE = 1;
    private String MODEL = "text-davinci-003";

    private Map<String, Object> outputGptMap;
    public Map<String, Object> getOutputGptMap() {
        return outputGptMap;
    }

    public void gerarTreino() {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost
                    ("https://api.openai.com/v1/completions");

            StringEntity inputGptEntity = new StringEntity
                    ("{" +
                            "\"model\" : " +
                            "\"" +
                            MODEL +
                            "\"," +
                            "\"prompt\" : \"" +
                            this.getPROMPT() +
                            "\"," +
                            "\"max_tokens\" : " +
                            MAX_TOKENS +
                            "," +
                            "\"temperature\" : " +
                            TEMPERATURE +
                            "}");

            System.out.println("Input Gpt: " + inputGptEntity);

            // - Setando parâmetros para efetuar a requisição HTTP na API da OpenAI
            inputGptEntity.setContentType("application/json");
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Authorization", "Bearer " + KEY);
            post.setEntity(inputGptEntity);

            // - Executando a requisição POST na API da OpenAI
            HttpResponse response = client.execute(post);

            //  verificar se a requisição http foi executada com sucesso
            if (response.getStatusLine().getStatusCode() != 201)
                System.out.println("HTTP Status Code: " + response.getStatusLine().getStatusCode());

            // - Passando o input para a requisição POST
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader( response.getEntity().getContent() ));

            String outputGpt;
            System.out.println("\n\nGPT Resposta: \n");

            ObjectMapper jsonOutput = new ObjectMapper();


            while (( outputGpt = reader.readLine() ) != null) {
                System.out.println(outputGpt);
                outputGptMap = jsonOutput.readValue(outputGpt, Map.class);
            }

            // - Fechando/Desligando conexão
            client.getConnectionManager().shutdown();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

}