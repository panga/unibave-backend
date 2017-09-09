package com.example.unibave.rest.resource;

import com.example.unibave.rest.model.Aluno;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AlunoResource {

    @POST
    Response adiciona(@Valid Aluno novoAluno);

    @PUT
    @Path(value = "{codigo}")
    Response atualiza(@PathParam(value = "codigo") Long codigo, @Valid Aluno aluno);

    @GET
    @Path(value = "{codigo}")
    Response busca(@PathParam(value = "codigo") Long codigo);

    @DELETE
    @Path(value = "{codigo}")
    Response deleta(@PathParam(value = "codigo") Long codigo);

    @GET
    Response lista(@QueryParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("limit") @DefaultValue("10") int limit,
            @QueryParam("sort") @DefaultValue("codigo") String sort,
            @QueryParam("direction") @DefaultValue("asc") String direction);

}
