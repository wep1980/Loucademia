package br.com.wepdev.loucademia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.AcessoService;
import br.com.wepdev.loucademia.application.utils.ValidationException;
import br.com.wepdev.loucademia.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable{
	private static final long serialVersionUID = 1L;


	@EJB
	private AcessoService acessoService;
	
	@Inject
	private FacesContext facesContext;
	
	private String matricula;
	
	private Integer rg;
	
	
	
	public String registrarAcesso() {
	
		TipoAcesso tipoAcesso; 
		try {
			tipoAcesso = acessoService.registrarAcesso(matricula, rg);
			
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		
		String msg;
		if(tipoAcesso == TipoAcesso.Entrada) {
			msg = "ENTRADA registrada!";
			
		} else if (tipoAcesso == TipoAcesso.Saida) {
			msg = "SAÍDA registrada!";
			
		} else {
			msg = "Dados de registro de acesso inconsistentes";
		}
		
		facesContext.addMessage(null, new FacesMessage(msg));
		return null; // Volta para a propria pagina
	}
	
	

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}
	
	
	

}
