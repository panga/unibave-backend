package com.example.unibave.rest.model;

import lombok.Data;

@Data
public class AlunoDTO {

    private Long codigo;
    
    private String nome;
    
    private String cidade;

    public AlunoDTO(Aluno aluno) {
        this.codigo = aluno.getCodigo();
        this.nome = aluno.getNome();
        this.cidade = aluno.getCidade();
    }
    
}
