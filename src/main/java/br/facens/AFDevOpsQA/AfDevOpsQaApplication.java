package br.facens.AFDevOpsQA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class AfDevOpsQaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfDevOpsQaApplication.class, args);
	}
	
	@RequestMapping("/")
	@ResponseBody
	String home(){
			return "Olá, sejá bem-vinda! Este projeto faz parte da avaliação da AF da disciplina de DevOps e QA da Facens!";
	}
		
}
