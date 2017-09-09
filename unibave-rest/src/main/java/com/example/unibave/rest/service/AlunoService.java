package com.example.unibave.rest.service;

import com.example.unibave.rest.model.Aluno;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    Page<Aluno> lista(Pageable pageable, String nome);

}
