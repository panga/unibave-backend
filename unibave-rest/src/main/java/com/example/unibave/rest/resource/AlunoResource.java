package com.example.unibave.rest.resource;

import com.example.unibave.rest.model.Aluno;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@Api(value = "Cadastro de Alunos", tags = "alunos",
        consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
@Path("/alunos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AlunoResource {

    @ApiOperation(value = "Adiciona aluno")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Aluno adicionado com sucesso", response = Aluno.class)
    })
    @POST
    Response adiciona(@Valid final Aluno aluno);

    @ApiOperation(value = "Atualiza aluno")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Aluno atualizado com sucesso", response = Aluno.class)
        ,
        @ApiResponse(code = 404, message = "Aluno não encontrado")
    })
    @PUT
    @Path(value = "{codigo}")
    Response atualiza(@PathParam(value = "codigo") final Long codigo, @Valid final Aluno aluno);

    @ApiOperation(value = "Deleta aluno")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Aluno deletado com sucesso")
        ,
        @ApiResponse(code = 404, message = "Aluno não encontrado")
    })
    @DELETE
    @Path(value = "{codigo}")
    Response deleta(@PathParam(value = "codigo") final Long codigo);

    @ApiOperation(value = "Busca aluno")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Aluno retornado com sucesso", response = Aluno.class)
        ,
        @ApiResponse(code = 404, message = "Aluno não encontrado")
    })
    @GET
    @Path(value = "{codigo}")
    Response busca(@PathParam(value = "codigo") final Long codigo);

    @ApiOperation(value = "Lista alunos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de alunos", response = Aluno.class, responseContainer = "List")
    })
    @GET
    Response lista(@QueryParam(value = "nome") final String nome,
            @QueryParam(value = "page") @DefaultValue("0") final int page,
            @QueryParam(value = "limit") @DefaultValue("10") final int limit,
            @QueryParam(value = "sort") @DefaultValue("codigo") final String sort,
            @QueryParam(value = "direction") @DefaultValue("asc") final String direction);
}
