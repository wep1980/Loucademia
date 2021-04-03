package br.com.wepdev.loucademia.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wepdev.loucademia.application.utils.StringUtils;
import br.com.wepdev.loucademia.application.utils.Validation;
import br.com.wepdev.loucademia.application.utils.ValidationException;
import br.com.wepdev.loucademia.domain.aluno.Aluno;
import br.com.wepdev.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {
	

	@EJB
	private AlunoRepository alunoRepository;
	
	
	/**
	 * Metodo que cadastra ou atualiza um aluno
	 * @param aluno
	 */
	public void cadastraOuAtualiza(Aluno aluno) {
		
		if(StringUtils.isEmpty(aluno.getMatricula())) {
			criarAluno(aluno);
		} else {
			atualizarAluno(aluno);
		}
	}
	
	
	/*
	 * Metodo privado so pode ser chamado dentro da propria classe
	 */
	private void criarAluno(Aluno aluno) {
		Validation.assertNotEmpty(aluno); // Verifica realmente se tem aluno
		
		String maxMatricula = alunoRepository.getMaxMatriculaAno(); // Capturando a maior matricula cadastrada do ano atual no sistema
		
		aluno.gerarMatricula(maxMatricula); // Gera a matricula do aluno
		alunoRepository.cadastrar(aluno); // Cadastra o aluno
	}
	
	
	/*
	 * Metodo privado so pode ser chamado dentro da propria classe
	 */
	private void atualizarAluno(Aluno aluno) {
		Validation.assertNotEmpty(aluno); // Verifica realmente se tem aluno
		Validation.assertNotEmpty(aluno.getMatricula()); // Verifica se o aluno tem uma matricula associada
		
		alunoRepository.update(aluno);
		
	}
	
	
	/**
	 * Busca um aluno por matricula
	 * @param matricula
	 * @return
	 */
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	
	/**
	 * Metodo que lista os alunos, utilizado na tela pesquisaDeAluno.xhtml
	 * @param matricula
	 * @param nome
	 * @param rg
	 * @param telefone
	 * @return
	 */
	public List<Aluno> listAlunos(String matricula , String nome , Integer rg , Integer telefone){
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo ao menos um critério de pesquisa deve ser fornecido");
		}
		return alunoRepository.listAlunos(matricula, nome, rg, telefone);
	
	}
	
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}
	
	
	
	
	
	
	
	

}
