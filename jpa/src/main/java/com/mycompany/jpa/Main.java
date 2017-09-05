package com.mycompany.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {
        //Cria o persistence manager
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        final EntityManager em = emf.createEntityManager();
        try {
            final EntityTransaction tx = em.getTransaction();
            try {
                //Inicia transação
                tx.begin();

                //Cadastra um aluno
                final Aluno a1 = new Aluno();
                a1.setNome("João");
                em.persist(a1); //Persiste aluno
                LOG.info("Aluno = {}", a1);

                //Finaliza transação
                tx.commit();
            } catch (final Exception ex) {
                ex.printStackTrace();
                //Rollback em caso de erro
                tx.rollback();
            }
        } finally {
            //Finaliza o persistence manager
            em.close();
            emf.close();
        }
    }
}
