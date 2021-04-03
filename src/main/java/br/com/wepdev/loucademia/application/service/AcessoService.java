package br.com.wepdev.loucademia.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wepdev.loucademia.application.utils.StringUtils;
import br.com.wepdev.loucademia.application.utils.ValidationException;
import br.com.wepdev.loucademia.domain.acesso.Acesso;
import br.com.wepdev.loucademia.domain.acesso.AcessoRepository;
import br.com.wepdev.loucademia.domain.acesso.TipoAcesso;
import br.com.wepdev.loucademia.domain.aluno.Aluno;
import br.com.wepdev.loucademia.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {


	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepository alunoRepository;
	
	
	/**
	 * Metodo de acesso do aluno de entrada ou de saida e retorna o tipo de acesso, que é um Enum (Entrada , Saida)
	 * @param matricula
	 * @param rg
	 */
	public TipoAcesso registrarAcesso(String matricula , Integer rg) {
		if(StringUtils.isEmpty(matricula) && rg == null) {
			throw new ValidationException("É preciso fornencer a matrícula ou o RG do aluno");
		}
		
		Aluno aluno;
		if(StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByRG(rg);
			
		} else {
			aluno = alunoRepository.findByMatricula(matricula);
		}
		
		if(aluno == null) {
			throw new ValidationException("O Aluno não foi encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		
		// Se aluno nunca entrou na academia, ou seja não tem um ultimo acesso ou se o ultimoAcesso que veio do BD tem uma entrada e uma saida preenchidas, tera que ser criado um acesso novo de entrada.
		if(ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) { 
			ultimoAcesso = new Acesso(); // Cria um novo acesso
			ultimoAcesso.setAluno(aluno); // Seta o aluno nesse novo acesso
			tipoAcesso = ultimoAcesso.registrarAcesso(); // E registra um novo acesso de entrada nesse caso.
			acessoRepository.cadastrarNovoAcesso(ultimoAcesso); // cadastra no banco de dados
			
		} else {
			// Se aluno ja tiver um registro de entrada mas não tem o de saída preenchido
			tipoAcesso = ultimoAcesso.registrarAcesso(); // Registrar o acesso de saida
		}
		
		return tipoAcesso;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
