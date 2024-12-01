package br.facens.AFDevOpsQA.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.facens.AFDevOpsQA.dto.PessoaDTO;
import br.facens.AFDevOpsQA.entity.Pessoa;
import br.facens.AFDevOpsQA.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

	@Autowired
    private PessoaService pessoaService;
	
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Pessoa> cadastrarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.cadastrarPessoa(pessoaDTO);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED); 
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDTO>> listarPessoas() {
        List<PessoaDTO> pessoas = pessoaService.listarPessoas()
                .stream()
                .map(p -> new PessoaDTO(p.getId(), p.getNome(), p.getPartido(), p.getQtdVotos()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping("/{id}/votar")
    public ResponseEntity<PessoaDTO> votar(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.votar(id);
        return ResponseEntity.ok(new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getPartido(), null));
    }

    @GetMapping("/{id}/votos")
    public ResponseEntity<PessoaDTO> contagemDeVotos(@PathVariable Long id) {
    	Pessoa pessoa = pessoaService.contagemDeVotos(id);
        return ResponseEntity.ok(new PessoaDTO(pessoa.getId(), pessoa.getNome(), pessoa.getPartido(), pessoa.getQtdVotos()));
    }
	
}
