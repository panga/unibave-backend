package com.example.unibave.rest;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {
 
    @Inject
    private EntityManager em;
    
    @GET
    public Response lista() {
        List<Aluno> alunos = em.createQuery(
                "SELECT a FROM Aluno a").getResultList();
        return Response.ok(alunos).build();
    }
    
    @POST
    public Response adiciona(Aluno novoAluno) {
        em.persist(novoAluno);
        return Response.ok(novoAluno).build();
    }
    
    @PUT
    @Path("{codigo}")
    public Response atualiza(@PathParam("codigo") Long codigo,
            Aluno aluno) {
        if (!codigo.equals(aluno.getCodigo())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
        Aluno alunoSalvo = em.merge(aluno);
        return Response.ok(alunoSalvo).build();
    }
    
    @GET
    @Path("{codigo}")
    public Response busca(@PathParam("codigo") Long codigo) {
        Aluno aluno = em.find(Aluno.class, codigo);
        return Response.ok(aluno).build();
    }
    
    @DELETE
    @Path("{codigo}")
    public Response deleta(@PathParam("codigo") Long codigo) {
        em.remove(em.getReference(Aluno.class, codigo));
        return Response.noContent().build();
    }
}
