package com.unibave.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(
        {@NamedQuery(name = "aluno.lista", 
                query = "SELECT a FROM Aluno a"),
        @NamedQuery(name = "aluno.listaPorCidade", 
                query = "SELECT a FROM Aluno a WHERE a.cidade LIKE :cidade")})
public class Aluno {

    @Id
    @GeneratedValue
    private Long codigo;

    @Column(length = 100, nullable = false)
    private String nome;
    
    @Column(length = 100, nullable = false)
    private String cidade;
    
    @OneToMany(mappedBy = "aluno",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlunoContato> contatos = new ArrayList<>();

    public Long getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return cidade;
    }

    public List<AlunoContato> getContatos() {
        return Collections.unmodifiableList(contatos);
    }
    
    public void addContato(AlunoContato contato) {
        contato.setAluno(this);
        contatos.add(contato);
    }
    
    public void removeContato(AlunoContato contato) {
        contatos.remove(contato);
    }

    @Override
    public String toString() {
        return "Aluno{" + "codigo=" + codigo 
                + ", nome=" + nome 
                + ", cidade=" + cidade + '}';
    }
    
}
