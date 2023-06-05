package com.brokenbrain.agro.terreno.repository;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.terreno.model.Terreno;
import jakarta.persistence.*;

import java.util.List;

public class TerrenoRepository{

    public List<Terreno> consultarTerrenoPorID(EntityManager manager, Long id) {
        List<Terreno> listTerreno = null;
        try {
            listTerreno = (List<Terreno>) manager
                    .createQuery("SELECT a FROM Terreno a WHERE a.id = :id", Terreno.class)
                    .setParameter("id", id)
                    .getResultList();
            listTerreno.forEach(
                    terreno -> System.out.println(
                            "\n\nTamnho(m²): "
                            + terreno.getQtdEspaco()
                            +"\nEstação: "
                            + terreno.getNmEstacao()
                            +"\nAgricultor: "
                            + terreno.getAgricultor().getUsername()
                            + "\n\n"
                    ));
        } catch (NoResultException e) {
            System.out.println("Agricultor não encontrado para o id: " + id);
        } return listTerreno;
    }



}
