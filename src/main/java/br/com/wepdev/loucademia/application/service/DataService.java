package br.com.wepdev.loucademia.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.wepdev.loucademia.domain.aluno.Estado;
import br.com.wepdev.loucademia.domain.aluno.EstadoRepository;
import br.com.wepdev.loucademia.domain.aluno.Aluno.Sexo;
import br.com.wepdev.loucademia.domain.aluno.Aluno.Situacao;

/*
 * Classe responsavel pelos servi�os
 */
@Stateless // Componente que executa uma logica de negocio *** O EJB Stateless e um ejb que n�o mantem estado, tudo que e executado tem que terminar no metodo que foi chamado( N�O PODE SER USADO ATRIBUTOS PARA ARMAZENAR INFORMA��ES )
public class DataService {
	

	@EJB // A injec�o de referencia e do tipo EJB Stateless
	private EstadoRepository estadoRepository;
	
	
	/**
	 * Lista os estados chamando o metodo do EstadoRepository
	 * @return
	 */
    public List<Estado> listEstados(){
    	return estadoRepository.listEstados();
    }
    
    

	/**
	 * Retorna um array com todos os elementos do Enum de sexo da classe Aluno( Masculino ,  Feminino )
	 * @return
	 */
	public Sexo[] getSexos() {
		return Sexo.values();
	}
	
	/**
	 * Retorna um array com todos os elementos do Enum de situa��o da classe Aluno( Ativo ,  Inativo , Pendencia )
	 * @return
	 */
	public Situacao[] getSituacoes() {
		return Situacao.values();
	}

}
