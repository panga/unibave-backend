package com.example.unibave.rest.repository;

import com.example.unibave.rest.model.Aluno;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository 
        extends CrudRepository<Aluno, Long> {
    
    List<Aluno> findByNomeContaining(String nome);
}
