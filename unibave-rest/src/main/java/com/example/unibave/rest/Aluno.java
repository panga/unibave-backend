package com.example.unibave.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Aluno {
    
    @Id
    @GeneratedValue
    private Long codigo;
    
    private String nome;
    
    private String cidade;
}
