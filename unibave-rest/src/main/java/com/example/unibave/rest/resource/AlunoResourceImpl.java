package com.example.unibave.rest.resource;

import com.example.unibave.rest.model.Aluno;
import com.example.unibave.rest.model.AlunoDTO;
import com.example.unibave.rest.service.AlunoService;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

@Component
public class AlunoResourceImpl implements AlunoResource {
 
    @Inject
    private AlunoService service;

    @Override
    public Response lista(String nome) {
        return Response.ok(service.lista(nome)).build();
    }

    @Override
    public Response adiciona(Aluno novoAluno) {
        return Response.ok(service.adiciona(novoAluno)).build();
    }

    @Override
    public Response atualiza(Long codigo,
            Aluno aluno) {
        return Response.ok(service.atualiza(codigo, aluno)).build();
    }

    @Override
    public Response busca(Long codigo) {
        Aluno aluno = service.busca(codigo)
                .orElseThrow(() -> new NotFoundException());
        return Response.ok(new AlunoDTO(aluno)).build();
    }

    @Override
    public Response deleta(Long codigo) {
        service.deleta(codigo);
        return Response.noContent().build();
    }
}
