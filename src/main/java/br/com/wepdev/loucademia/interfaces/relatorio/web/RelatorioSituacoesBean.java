package br.com.wepdev.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.wepdev.loucademia.application.service.AlunoService;
import br.com.wepdev.loucademia.domain.aluno.Aluno;
import br.com.wepdev.loucademia.domain.aluno.Aluno.Situacao;

@Named
@SessionScoped // Garante que o Bean vai ficar ativo enquanto o navegador do usuario estiver aberto( UTILIZADO PARA MULTIPLAS REQUISIÇÕES NA MESMA JANELA DO NAVEGADOR )
public class RelatorioSituacoesBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private AlunoService alunoService;
	
	private Situacao situacao;
	
	private List<Aluno> alunos;
	
	
	
	public String gerarRelatorioSituacoes() {
		alunos = alunoService.listSituacoesAlunos(situacao);
		return null;
	}
	

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	

	
}
