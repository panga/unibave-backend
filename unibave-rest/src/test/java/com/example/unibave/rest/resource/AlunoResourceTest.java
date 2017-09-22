package com.example.unibave.rest.resource;

import com.example.unibave.rest.model.Aluno;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment 
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AlunoResourceTest {
    
    @Inject
    private TestRestTemplate restTemplate;
    
    private static final String BASE_URI = "/api/alunos";
    
    @Test
    public void adicionaAluno() {
        final Aluno aluno = Aluno
                .builder()
                .nome("Leonardo")
                .cidade("Criciuma")
                .build();
        ResponseEntity<Aluno> response = restTemplate
                .postForEntity(BASE_URI, aluno, Aluno.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Aluno alunoCriado = response.getBody();
        assertThat(alunoCriado.getCodigo()).isNotNull();
        assertThat(alunoCriado.getNome()).isEqualTo(aluno.getNome());
        assertThat(alunoCriado.getCidade()).isEqualTo(aluno.getCidade());
        assertThat(alunoCriado.getIdade()).isEqualTo(aluno.getIdade());
        
    }
    
}
