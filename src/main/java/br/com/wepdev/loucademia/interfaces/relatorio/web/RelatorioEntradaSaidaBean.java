package br.com.wepdev.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.AlunoService;
import br.com.wepdev.loucademia.application.utils.StringUtils;
import br.com.wepdev.loucademia.application.utils.ValidationException;
import br.com.wepdev.loucademia.domain.acesso.Acesso;

@Named
@RequestScoped
public class RelatorioEntradaSaidaBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private FacesContext facesContext;

	@EJB
	private AlunoService alunoService;
	
	private String matricula;
	
	private LocalDate dataInicial; // LocalDate para pegar somente o DIA
	
	private LocalDate dataFinal; // LocalDate para pegar somente o DIA
	
	
	private List<Acesso> acessos;
	
	
	/*
	 * Metodo que carrega o aluno quando o botao de relatorios de entrada e saidas da pagina pesquisaDeAluno.xhtml e botão Editar e clicado
	 */
	public void carregarAluno(){
		if(!StringUtils.isEmpty(matricula)) {
			gerarRelatorioEntradasESaidas();
		}
	}

	public String gerarRelatorioEntradasESaidas() {
		try {
			acessos = alunoService.listAcessosAlunos(matricula, dataInicial, dataFinal);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	

	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public LocalDate getDataInicial() {
		return dataInicial;
	}


	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}


	public LocalDate getDataFinal() {
		return dataFinal;
	}


	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
	}


	public List<Acesso> getAcessos() {
		return acessos;
	}
	
	
	
	

}
