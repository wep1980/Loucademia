package br.com.wepdev.loucademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.AlunoService;
import br.com.wepdev.loucademia.domain.aluno.Aluno;

@Named // Gerencia o ciclo de vida dos objetos
@RequestScoped // Esse objeto vai existir somente durante o processo de requisição(Request)
public class AlunoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@EJB
    private AlunoService alunoService;
	
	
	private Aluno aluno = new Aluno();
	
	
	public String cadastrar() {
		alunoService.cadastraOuAtualiza(aluno);
		return null;
	}
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	

}
