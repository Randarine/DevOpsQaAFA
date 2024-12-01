package br.facens.AFDevOpsQA.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.facens.AFDevOpsQA.dto.PessoaDTO;
import br.facens.AFDevOpsQA.entity.Pessoa;
import br.facens.AFDevOpsQA.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
    private PessoaRepository pessoaRepository;
	
    public Pessoa cadastrarPessoa(PessoaDTO pessoaDTO) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setPartido(pessoaDTO.getPartido());
        return pessoaRepository.save(pessoa);
    }
    
    public List<PessoaDTO> listarPessoas() {
        return pessoaRepository.findAll().stream()
                .map(pessoa -> {
                    PessoaDTO dto = new PessoaDTO();
                    dto.setNome(pessoa.getNome());
                    dto.setPartido(pessoa.getPartido());
                    return dto;
                })
                .collect(Collectors.toList());	
    }

    public Pessoa votar(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
        pessoa.adicionarVoto();
        return pessoaRepository.save(pessoa);
    }

    public Pessoa contagemDeVotos(Long id) {
    	return pessoaRepository.findById(id).orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));
    }
	
	
}	
