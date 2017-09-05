package com.unibave.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("unibave-jpa");
        //org.hibernate.jpa.HibernatePersistenceProvider
        
        //adiciona
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Aluno ramon = new Aluno();
        ramon.setNome("Ramon");
        ramon.setCidade("Cidade Azul");

        AlunoContato contato = new AlunoContato();
        contato.setTelefone("48999999999");
        //em.persist(contato);
        ramon.addContato(contato);
        
        em.persist(ramon);
        tx.commit();
        em.close();
        //lista
        em = emf.createEntityManager();
        List<Aluno> alunos = em.createNamedQuery("aluno.lista", Aluno.class)
                .setMaxResults(10)
                .getResultList();
        alunos.forEach(a -> {
            System.out.println(a);
            a.getContatos().forEach(System.out::println);
        });
        em.close();
        //lista por cidade
        em = emf.createEntityManager();
        alunos = em.createNamedQuery("aluno.listaPorCidade", Aluno.class)
                .setParameter("cidade", "Cidade Azul")
                .setMaxResults(10)
                .getResultList();
        alunos.forEach(System.out::println);
        em.close();
        //atualiza
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        ramon = em.find(Aluno.class, ramon.getCodigo());
        ramon.setCidade("Tubarão");
        em.merge(ramon);
        tx.commit();
        //deleta
        tx = em.getTransaction();
        tx.begin();
        em.remove(ramon);
        tx.commit();
        em.close();      
        
        emf.close();
    }
}
