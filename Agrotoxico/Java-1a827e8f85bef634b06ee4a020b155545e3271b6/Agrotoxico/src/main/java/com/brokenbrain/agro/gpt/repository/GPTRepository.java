package com.brokenbrain.agro.gpt.repository;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.gpt.model.RespostaGPT;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class GPTRepository {

    public List<RespostaGPT> consultarRespostaPorId(EntityManager manager, Long id) {
        List<RespostaGPT> listResposta = null;
        try {
            listResposta = (List<RespostaGPT>)
                    manager.createQuery(
                            "SELECT a FROM RespostaGPT a WHERE a.id = :id", RespostaGPT.class
                            )
                    .setParameter("id", id)
                    .getResultList();
            listResposta.forEach(
                    resposta -> System.out.println(
                            "\n\nNome da Planta: "
                            + resposta.getNomePlanta()
                            +"\nJustificativa: "
                            + resposta.getJustificativa()
                            +"\nTempo para colher: "
                            + resposta.getTempo()
                            +"\nEstimativa de colheita: "
                            + resposta.getColheita()
                            + "\n\n"
                    ));
        } catch (NoResultException e) {
            System.out.println("Resposta não encontrada para o id: " + id);
        }
        return listResposta;

    }

}
