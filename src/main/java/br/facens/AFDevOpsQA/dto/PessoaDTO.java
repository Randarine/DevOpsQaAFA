package br.facens.AFDevOpsQA.dto;

import br.facens.AFDevOpsQA.entity.Pessoa;

public class PessoaDTO {

    private Long id;
	private String nome;
    private String partido;
    private Integer qtdVotos;
    
    public PessoaDTO() {}
    
    
    public PessoaDTO(Long Id, String nome, String partido, Integer qtdVotos) {
        this.id = Id;
    	this.nome = nome;
        this.partido = partido;
        this.qtdVotos = qtdVotos;
    }

    public PessoaDTO(Long Id, String nome, String partido) {
    	this.id = Id;
        this.nome = nome;
        this.partido = partido;
    }
    
    public PessoaDTO(String nome, String partido, Integer qtdVotos) {
        this.nome = nome;
        this.partido = partido;
        this.qtdVotos = qtdVotos;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPartido() {
		return partido;
	}

	public void setPartido(String partido) {
		this.partido = partido;
	}
	
	public Integer getQtdVotos() {
		return qtdVotos;
	}

	public void setQtdVotos(Integer qtdVotos) {
		this.qtdVotos = qtdVotos;
	}
	
    public static PessoaDTO fromEntity(Pessoa pessoa) {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setPartido(pessoa.getPartido());

        return pessoaDTO;
    }
	
}
