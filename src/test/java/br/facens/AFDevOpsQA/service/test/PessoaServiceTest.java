package br.facens.AFDevOpsQA.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.facens.AFDevOpsQA.dto.PessoaDTO;
import br.facens.AFDevOpsQA.entity.Pessoa;
import br.facens.AFDevOpsQA.repository.PessoaRepository;
import br.facens.AFDevOpsQA.service.PessoaService;

public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    public void testCadastrarPessoa() {
	        PessoaDTO pessoaDTO = new PessoaDTO();
	        pessoaDTO.setNome("João da Silva");
	        pessoaDTO.setPartido("Partido Gerado");
	        
	        Pessoa mockPessoa = new Pessoa(1L, "João da Silva", "Partido Gerado", 0);

	        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(mockPessoa);

	        Pessoa pessoaCadastrada = pessoaService.cadastrarPessoa(new PessoaDTO(1L, "João da Silva", "Partido Gerado"));

	        assertNotNull(pessoaCadastrada);
	        assertEquals("João da Silva", pessoaCadastrada.getNome());
	        assertEquals("Partido Gerado", pessoaCadastrada.getPartido());
	        assertEquals(0, pessoaCadastrada.getQtdVotos());
	        Mockito.verify(pessoaRepository, times(1)).save(Mockito.any(Pessoa.class));
	    }

	    @Test
	    public void testListarPessoas() {
	        Pessoa pessoa1 = new Pessoa();
	        pessoa1.setId(1L);
	        pessoa1.setNome("João da Silva");
	        pessoa1.setPartido("Partido Gerado");

	        Pessoa pessoa2 = new Pessoa();
	        pessoa2.setId(2L);
	        pessoa2.setNome("Maria de Fátima");
	        pessoa2.setPartido("Partido Criado");

	        Mockito.when(pessoaRepository.findAll()).thenReturn(List.of(pessoa1, pessoa2));

	        List<PessoaDTO> pessoas = pessoaService.listarPessoas();

	        assertEquals(2, pessoas.size());
	        Mockito.verify(pessoaRepository, times(1)).findAll();
	    }

	    @Test
	    public void testVotarEmPessoa() {
	        Pessoa pessoa = new Pessoa();
	        pessoa.setId(1L);
	        pessoa.setNome("João da Silva");
	        pessoa.setPartido("Partido Gerado");
	        pessoa.setQtdVotos(1);

	        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
	        Mockito.when(pessoaRepository.save(Mockito.any(Pessoa.class))).thenReturn(pessoa);

	        Pessoa pessoaVotada = pessoaService.votar(1L);

	        assertNotNull(pessoaVotada);
	        assertEquals(2, pessoaVotada.getQtdVotos());
	        Mockito.verify(pessoaRepository, times(1)).findById(1L);
	        Mockito.verify(pessoaRepository, times(1)).save(pessoa);
	    }

	    @Test
	    public void testContagemDeVotos() {
	        Pessoa pessoa = new Pessoa();
	        pessoa.setId(1L);
	        pessoa.setNome("João da Silva");
	        pessoa.setPartido("Partido Gerado");
	        pessoa.setQtdVotos(3);

	        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

	        Pessoa pessoaComVotos = pessoaService.contagemDeVotos(1L);

	        assertNotNull(pessoaComVotos);
	        assertEquals(3, pessoaComVotos.getQtdVotos());
	        Mockito.verify(pessoaRepository, times(1)).findById(1L);
	    }
}
