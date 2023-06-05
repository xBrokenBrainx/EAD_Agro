package com.brokenbrain.agro.agricultor.repository;

import com.brokenbrain.agro.agricultor.model.Agricultor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public class AgricultorRepository {

    public List<Agricultor> consultarAgricultorPorId(EntityManager manager, Long id) {
        List<Agricultor> listAgricultor = null;
        try {
            listAgricultor = (List<Agricultor>) manager
                    .createQuery("SELECT a FROM Agricultor a WHERE a.id = :id", Agricultor.class)
                    .setParameter("id", id)
                    .getResultList();
            listAgricultor.forEach(
                    agricultor -> System.out.println(
                        "\n\nUsername: "
                        + agricultor.getUsername()
                        +"\nCidade: "
                        +agricultor.getCidade()
                        + "\n\n"
            ));
        } catch (NoResultException e) {
            System.out.println("Agricultor não encontrado para o id: " + id);
        } return listAgricultor;
    }


}
