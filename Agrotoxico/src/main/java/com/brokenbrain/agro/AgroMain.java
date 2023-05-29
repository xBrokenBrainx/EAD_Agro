package com.brokenbrain.agro;


import com.brokenbrain.agro.agricultor.model.Agricultor;
import com.brokenbrain.agro.gpt.model.GPTAgro;
import com.brokenbrain.agro.respostaplantio.model.RespostaPlantio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AgroMain {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        var usuario = new Agricultor();
        usuario.setQtdEspaco((float)10);
        usuario.setCidade("São Paulo");

        var resposta = new RespostaPlantio();
        resposta.setAgricultor(usuario);
        resposta.setDtColheita(6);
        resposta.setQtdOpcoes(5);

        var gpt = new GPTAgro();
        gpt.setAgricultor(usuario);
        gpt.setRespostaPlantio(resposta);

        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.persist(resposta);
        manager.persist(gpt);
        manager.getTransaction().commit();
        manager.close();
        factory.close();


        /*
        // - Instanciando EntityManager e setando banco de dados
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        // - Instanciando Paciente(usuario) com Nome, Data de nascimento, Peso, Altura e Descrição da deficiência
        var usuario = new Agricultor();
        usuario.setNome("Alvaro");
        usuario.setDtNasc(LocalDate.now().minusYears(38));
        usuario.setPeso(58);
        usuario.setAltura(1.77f);
        usuario.setDescDeficiencia("Braco esquerdo amputado na altura do ombro");

        // - Instanciando Treino com quantidade de dias de treino, data de início e data de término(com base na qtd de dias de treino)
        // - e descrição da deficiência. Esses dados são usados no input da API da OpenAI
        var treino = new RespostaPlantio();
        treino.setPaciente(usuario)
                .setQtdDias(5)
                .setDtInicio(LocalDateTime.now().plusDays(1))
                .setDtFim(LocalDateTime.now().plusDays(6))
                .setDescDeficiencia(usuario.getDescDeficiencia());

        // - Instanciando GPT e setando o treino.
        var gpt = new GPT(treino);

        // - getInputGptPrompt é o método que gera a String que será colocada como input na API da OpenAI para Gerar o treino
        String prompt = gpt.getInputGptPrompt();

        // - Instanciando service, passando o prompt de input e recebendo output através do método gerarTreino
        GptService service = new GptService();
        service.setPROMPT(prompt);
        service.gerarTreino();
        gpt.setOutputGpt( service.getOutputGptMap().toString() );
        System.out.println( service );

        // - Usando o manager(Instância de EntityManager) para fazer persistência dos dados
        manager.getTransaction().begin();
        manager.persist(gpt);
        manager.getTransaction().commit();
        manager.close();
        factory.close();
        */

    }

}
