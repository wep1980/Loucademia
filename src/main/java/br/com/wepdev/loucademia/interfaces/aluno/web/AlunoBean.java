package br.com.wepdev.loucademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.AlunoService;
import br.com.wepdev.loucademia.application.utils.StringUtils;
import br.com.wepdev.loucademia.domain.aluno.Aluno;

@Named // Gerencia o ciclo de vida dos objetos
@RequestScoped // Esse objeto vai existir somente durante o processo de requisição(Request)
public class AlunoBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@EJB
    private AlunoService alunoService;
	
	@Inject
	private FacesContext facesContext; // Objeto que acessa o contexto da requisição
	
	
	private Aluno aluno = new Aluno();
	
	
	private String matricula; // Variavel que armazena o numero de matricula de um aluno para update
	
	private String titulo = "Novo aluno";
	
	
	/**
	 * Carrega um aluno por matricula, metodo executado na tela dadosAluno.xhtml
	 */
	public void carregar() {
		if(!StringUtils.isEmpty(matricula)) {
		   aluno = alunoService.findByMatricula(matricula);
		   titulo = "Alterar aluno"; // Altera o nome do titulo da pagina
		}
	}
	
	

	public String cadastrar() {
		alunoService.cadastraOuAtualiza(aluno);
		facesContext.addMessage(null, new FacesMessage("Dados gravados com sucesso!"));
		return null;
	}
	
	
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	

	public String getTitulo() {
		return titulo;
	}
}
