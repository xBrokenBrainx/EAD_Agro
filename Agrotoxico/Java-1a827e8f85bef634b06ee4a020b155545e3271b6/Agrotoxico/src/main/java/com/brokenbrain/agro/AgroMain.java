package com.brokenbrain.agro;


import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.agricultor.repository.AgricultorRepository;
import com.brokenbrain.agro.gpt.model.RespostaGPT;
import com.brokenbrain.agro.gpt.repository.GPTRepository;
import com.brokenbrain.agro.gpt.service.GPTService;
import com.brokenbrain.agro.terreno.model.Terreno;
import com.brokenbrain.agro.terreno.repository.TerrenoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class AgroMain {

    public static void main(String[] args) {

        var agricultor = new Agricultor();

        var scn = new Scanner(System.in);
        System.out.println( "Digite o username: " );
        agricultor.setUsername( scn.nextLine() );
        System.out.println( "Username: " + agricultor.getUsername() );
        System.out.println( "Digite a cidade: " );
        agricultor.setCidade(scn.nextLine() );
        System.out.println( "Cidade: " + agricultor.getCidade() );
        var terreno = new Terreno( agricultor );
        System.out.println("Digite a estacao para plantar: ");
        terreno.setNmEstacao( scn.nextLine() );
        System.out.println( "Estacao: " + terreno.getNmEstacao() );
        System.out.println( "Digite o espaco do terreno: " );
        terreno.setQtdEspaco( scn.nextFloat() );
        System.out.println( "Terreno: " + terreno.getAgricultor() );

        var resposta = new RespostaGPT();
        resposta.setAgricultor( agricultor );
        resposta.setTerreno( terreno );


        var gpt = new GPTService();
        gpt.setRespostaGPT( resposta );
        gpt.gerarPrompt( terreno );
        gpt.gerarRespostaPlantio();


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql-db");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist( agricultor );
        manager.persist( terreno );
        manager.persist( resposta );

        /*
        CONSULTAS JPQL
         */
        AgricultorRepository agricultorRepository = new AgricultorRepository();
        agricultorRepository.consultarAgricultorPorId(manager, 1L);
        GPTRepository gptRepository = new GPTRepository();
        gptRepository.consultarRespostaPorId(manager, 53L);
        TerrenoRepository terrenoRepository = new TerrenoRepository();
        terrenoRepository.consultarTerrenoPorID(manager, 102L);

        manager.getTransaction().commit();
        manager.close();
        factory.close();

    }
}