package com.example.unibave.rest.resource;

import com.example.unibave.rest.model.Aluno;
import com.example.unibave.rest.model.AlunoDTO;
import javax.inject.Inject;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment 
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlunoResourceTest {
    
    @Inject
    private TestRestTemplate restTemplate;
    
    private static Aluno alunoCriado = null;
    
    private static final String BASE_URI = "/api/alunos";
    
    @Test
    public void _01_adicionaAluno() {
        final Aluno aluno = Aluno
                .builder()
                .nome("Leonardo")
                .cidade("Criciuma")
                .build();
        ResponseEntity<Aluno> response = restTemplate
                .postForEntity(BASE_URI, aluno, Aluno.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        alunoCriado = response.getBody();
        assertThat(alunoCriado.getCodigo()).isNotNull();
        assertThat(alunoCriado.getNome()).isEqualTo(aluno.getNome());
        assertThat(alunoCriado.getCidade()).isEqualTo(aluno.getCidade());
        assertThat(alunoCriado.getIdade()).isEqualTo(aluno.getIdade());
    }
    
    @Test
    public void _02_atualizaAluno() {
        alunoCriado.setCidade("Orleans");
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/"
                        + alunoCriado.getCodigo(),
                        HttpMethod.PUT,
                        new HttpEntity<>(alunoCriado), Aluno.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Aluno alunoBusca = response.getBody();
        assertThat(alunoBusca.getCodigo()).isNotNull();
    }
    
    @Test
    public void _03_buscaAluno() {
        ResponseEntity<Aluno> response = restTemplate
                .exchange(BASE_URI + "/"
                        + alunoCriado.getCodigo(),
                        HttpMethod.GET, null, Aluno.class);
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        Aluno alunoBusca = response.getBody();
        assertThat(alunoBusca.getCodigo()).isNotNull();
        assertThat(alunoBusca.getCidade()).isEqualTo("Orleans");
    }
    
    @Test
    public void _04_listaAlunos() {
        ResponseEntity<PageDTO<Aluno>> response = restTemplate
                .exchange(BASE_URI,
                        HttpMethod.GET, null, getPageTypeReference());
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        PageDTO<Aluno> alunos = response.getBody();
        assertThat(alunos.getTotalElements()).isEqualTo(1);
        assertThat(alunos.getContent().size()).isEqualTo(1);
    }
    
    @Before
    public void rodaAntesDeCadaTeste() {
        System.out.println(">>>>>>> INICIANDO TESTE");
    }
    
    @After
    public void rodaDepoisDeCadaTeste() {
        System.out.println(">>>>>>> FINALIZANDO TESTE");
    }
    
    private ParameterizedTypeReference<PageDTO<Aluno>> 
        getPageTypeReference() {
        return new ParameterizedTypeReference<PageDTO<Aluno>>() {
        };
    }
}
