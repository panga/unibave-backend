package com.example.unibave.rest.service;

import com.example.unibave.rest.model.Aluno;
import com.example.unibave.rest.repository.AlunoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.inject.Inject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoServiceImpl implements AlunoService {

    @Inject
    private AlunoRepository repository;

    @Override
    @Transactional
    public Aluno adiciona(Aluno novoAluno) {
        return repository.save(novoAluno);
    }

    @Override
    @Transactional
    public Aluno atualiza(Long codigo, Aluno aluno) {
        if (!codigo.equals(aluno.getCodigo())) {
            throw new IllegalArgumentException("Código inválido");
        }
        return repository.save(aluno);
    }

    @Override
    public Optional<Aluno> busca(Long codigo) {
        return Optional.ofNullable(repository.findOne(codigo));
    }

    @Override
    @Transactional
    public void deleta(final Long codigo) {
        busca(codigo).ifPresent(repository::delete);
    }

    @Override
    public Page<Aluno> lista(Pageable pageable, String nome) {
        return nome != null
                ? repository.findByNomeContaining(pageable, nome)
                : repository.findAll(pageable);
    }

}
