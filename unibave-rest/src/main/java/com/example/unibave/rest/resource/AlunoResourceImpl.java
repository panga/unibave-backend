package com.example.unibave.rest.resource;

import com.example.unibave.rest.model.Aluno;
import com.example.unibave.rest.model.AlunoDTO;
import com.example.unibave.rest.service.AlunoService;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class AlunoResourceImpl implements AlunoResource {
 
    @Inject
    private AlunoService service;

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

    @Override
    public Response lista(String nome, int page, int limit, String sort, String direction) {
        Pageable pageagle = new PageRequest(page, limit, 
                Sort.Direction.fromString(direction), sort);
        return Response.ok(service.lista(pageagle, nome)).build();
    }
}
