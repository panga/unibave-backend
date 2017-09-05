package com.unibave.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AlunoContato {

    @Id
    @GeneratedValue
    private Long codigo;
    
    @ManyToOne
    private Aluno aluno;

    @Column(length = 11, nullable = false)
    private String telefone;

    public Long getCodigo() {
        return codigo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Aluno getAluno() {
        return aluno;
    }

    @Override
    public String toString() {
        return "AlunoContato{" + "codigo=" + codigo + ", telefone=" + telefone + '}';
    }

}
