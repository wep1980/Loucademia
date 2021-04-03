package br.com.wepdev.loucademia.interfaces.compartilhado.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.DataService;
import br.com.wepdev.loucademia.domain.aluno.Aluno.Sexo;
import br.com.wepdev.loucademia.domain.aluno.Aluno.Situacao;
import br.com.wepdev.loucademia.domain.aluno.Estado;

@Named
@ApplicationScoped
public class DataBean implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@EJB
	private DataService dataService;
	


	/**
	 * Retorna um array com todos os elementos do Enum de sexo da classe Aluno( Masculino ,  Feminino )
	 * @return
	 */
	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	/**
	 * Retorna um array com todos os elementos do Enum de situação da classe Aluno( Ativo ,  Inativo , Pendencia )
	 * @return
	 */
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}
	
	/**
	 * Retorna uma lista de Estados
	 * @return
	 */
	public List<Estado> getEstados(){
		return dataService.listEstados();
	}
	
	
	/**
	 * Metodo que formata o numero do telefone
	 * @param ddd
	 * @param numero
	 * @return
	 */
	public String formataTelefone(Integer ddd , Integer numero ) {
		if(ddd == null || numero == null) {
			return "";
		}
		return "(" + ddd + ")" + numero;
	}

}
