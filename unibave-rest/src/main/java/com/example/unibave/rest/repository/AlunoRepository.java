package com.example.unibave.rest.repository;

import com.example.unibave.rest.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository 
        extends PagingAndSortingRepository<Aluno, Long> {
    
    Page<Aluno> findByNomeContaining(Pageable pageable, String nome);
}
