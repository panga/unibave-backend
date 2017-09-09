package com.example.unibave.rest.service;

import com.example.unibave.rest.model.Aluno;
import java.util.List;
import java.util.Optional;

public interface AlunoService {

    Aluno adiciona(Aluno novoAluno);

    Aluno atualiza(Long codigo, Aluno aluno);

    /**
     * Busca o aluno
     * 
     * @param codigo Código do aluno
     * @return Aluno se existir
     */
    Optional<Aluno> busca(Long codigo);

    void deleta(Long codigo);

    List<Aluno> lista(String nome);

}
