package br.com.wepdev.loucademia.interfaces.relatorio.web;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.inject.Inject;
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
	
	
	@Inject
	@RequestParameterMap // Pega os parametros que vem na requisição incluindo os que vem na URL , a chave do Map vai ser o nome do parametro e o valor vai ser o valor do parametro
	private Map<String , String> requisicaoParametrosMap; // Variavel que 
	
	private Situacao situacao;
	
	private List<Aluno> alunos;
	
	
	
	/**
	 * Metodo que limpa a tela caso exista dados nela
	 */
	public void checarELimparTela() {
		String limpar = requisicaoParametrosMap.get("limpar");
		
		// Boolean.valueOf(limpar) -> Transforma uma String em Boolean , ou seja a condição e se for verdadeiro
		if(limpar != null && Boolean.valueOf(limpar)) { // Se for for verdadeiro e limpar diferente de nulo	
			situacao = null;
			alunos = null;
		}
	}
	
	
	
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
