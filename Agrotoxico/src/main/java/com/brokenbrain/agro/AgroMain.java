package com.brokenbrain.agro;


import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.gpt.model.RespostaGPT;
import com.brokenbrain.agro.gpt.service.GPTService;
import com.brokenbrain.agro.terreno.model.Terreno;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class AgroMain {

    public static void main(String[] args) {

        var agricultor = new Agricultor();
        agricultor.setUsername("newren");
        agricultor.setCidade("São Paulo");

        var terreno = new Terreno(agricultor);
        terreno.setNmEstacao("Primavera");
        terreno.setQtdEspaco(30F);

        var resposta = new RespostaGPT();
        resposta.setAgricultor( agricultor );
        resposta.setTerreno( terreno );

        var gpt = new GPTService();
        gpt.setRespostaGPT( resposta );
        gpt.gerarPrompt( terreno );
        gpt.gerarRespostaPlantio();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist( agricultor );
        manager.persist( resposta );
        manager.persist( terreno );
        manager.getTransaction().commit();
        manager.close();
        factory.close();

    }
}
