package br.facens.AFDevOpsQA.controller.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import br.facens.AFDevOpsQA.controller.PessoaController;
import br.facens.AFDevOpsQA.dto.PessoaDTO;
import br.facens.AFDevOpsQA.entity.Pessoa;
import br.facens.AFDevOpsQA.service.PessoaService;

@WebMvcTest(PessoaController.class) 
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PessoaService pessoaService; 
    
    @Test
    public void testCadastrarPessoa() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("João da Silva");
        pessoaDTO.setPartido("Partido Gerado");
        
        Pessoa mockPessoa = new Pessoa(1L, "João da Silva", "Partido Gerado");

        Mockito.when(pessoaService.cadastrarPessoa(Mockito.any(PessoaDTO.class))).thenReturn(mockPessoa);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoas/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"João da Silva\", \"partido\":\"Partido Gerado\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João da Silva"))
        		.andExpect(jsonPath("$.partido").value("Partido Gerado"))
        		.andExpect(jsonPath("$.qtdVotos").value("0"));
    }

    @Test
    public void testListarPessoas() throws Exception {
        PessoaDTO pessoa1 = new PessoaDTO(1L, "João da Silva", "Partido Gerado");
        PessoaDTO pessoa2 = new PessoaDTO(2L, "Maria de Fátima", "Partido Criado");

        Mockito.when(pessoaService.listarPessoas())
                .thenReturn(List.of(new PessoaDTO(1L, "João da Silva", "Partido Gerado"), new PessoaDTO(2L, "Maria de Fátima", "Partido Criado")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas/listar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("João da Silva"))
                .andExpect(jsonPath("$[0].partido").value("Partido Gerado"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nome").value("Maria de Fátima"))
                .andExpect(jsonPath("$[1].partido").value("Partido Criado"));
    }
    
    @Test
    public void testVotar() throws Exception {
        Pessoa pessoa = new Pessoa(1L, "João da Silva", "Partido Gerado");

        Mockito.when(pessoaService.votar(1L)).thenReturn(pessoa);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/pessoas/1/votar")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João da Silva"))
                .andExpect(jsonPath("$.partido").value("Partido Gerado"));
    }
    
    @Test
    public void testContagemDeVotos() throws Exception {
        Pessoa pessoa = new Pessoa(1L, "João da Silva", "Partido Gerado", 5);

        Mockito.when(pessoaService.contagemDeVotos(1L)).thenReturn(pessoa);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pessoas/1/votos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("João da Silva"))
                .andExpect(jsonPath("$.partido").value("Partido Gerado"))
                .andExpect(jsonPath("$.qtdVotos").value(5));
    }
    
    
}