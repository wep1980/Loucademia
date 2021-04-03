package br.com.wepdev.loucademia.domain.aluno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.wepdev.loucademia.application.utils.StringUtils;
import br.com.wepdev.loucademia.domain.acesso.Acesso;
import br.com.wepdev.loucademia.domain.aluno.Aluno.Situacao;

@Stateless
public class AlunoRepository {
	

	@PersistenceContext // Injecção de dependencia CDi
	private EntityManager entityManager; // Porta de entrada para JPA
	
	
	
	public void cadastrar(Aluno aluno) {
		entityManager.persist(aluno);
	}
	
	
	public void update(Aluno aluno) {
		entityManager.merge(aluno);
	}
	
	
	/*
	 * Busca um aluno por matricula
	 * Matricula e o ID do Aluno
	 */
	public Aluno findByMatricula(String matricula) {
		return entityManager.find(Aluno.class, matricula);
	}
	
	
	/**
	 * Busca um aluno por RG , JPQL
	 * @param rg
	 * @return
	 */
	public Aluno findByRG(Integer rg) {
		
		try {
			return entityManager.createQuery("SELECT a FROM Aluno a WHERE a.rg = :rg" , Aluno.class)
					.setParameter("rg", rg)
					.getSingleResult();
			
		} catch (NoResultException e) { // Evita a excessão da JPA quando a busca for feita por um RG de aluno que não exista
			return null; //Evita a excessão de uma matricula que não exista
		}
	}
	
	
	/*
	 * Para excluir um objeto primeiro ele precisa ser carregado
	 */
	public void delete(String matricula) {
		Aluno aluno = findByMatricula(matricula); // CARREGA O ALUNO
		
		if(aluno != null) { // SE O ALUNO EXISTIR É REMOVIDO
			entityManager.remove(aluno);
		}
	}
	
	
	/*
	 * Metodo utilizado na classe Aluno no gerarMatricula()
	 * Pega o maior numero de matricula cadastrada do ano atual
	 */
	public String getMaxMatriculaAno() {
		
		// Pegando a maior matricula de um aluno no ano atual
		return entityManager.createQuery("SELECT MAX(a.matricula) FROM Aluno a WHERE a.matricula LIKE :ano" , String.class) // Retorna no maximo 1 registro ou null
		.setParameter("ano", Year.now() + "%")
		.getSingleResult(); // Retorna um resultado
	}
	
	
	public List<Aluno> listAlunos(String matricula , String nome , Integer rg , Integer telefone){
		
		StringBuilder jpql = new StringBuilder("SELECT a FROM Aluno a WHERE ");
		
		if(!StringUtils.isEmpty(matricula)) {
			jpql.append("a.matricula = :matricula AND ");
		}
		
		if(!StringUtils.isEmpty(nome)) {
			jpql.append("a.nome LIKE :nome AND ");
		}
		
		if(rg != null) {
			jpql.append("a.rg = :rg AND ");
		}
		
		if(telefone != null) {
			jpql.append("(a.telefone.numeroCelular LIKE :celular OR a.telefone.numeroFixo LIKE :fixo) AND ");
		}
		
		jpql.append("1 = 1"); // Para finalizar a query e necessario uma condição verdadeira
		
		/************************************************************************************************************************************************************************/
		TypedQuery<Aluno> typedQuery = entityManager.createQuery(jpql.toString() , Aluno.class);
		
		if(!StringUtils.isEmpty(matricula)) {
			typedQuery.setParameter("matricula", matricula);
		}
		
		if(!StringUtils.isEmpty(nome)) {
			typedQuery.setParameter("nome", "%" + nome + "%");
		}
		
		if(rg != null) {
			typedQuery.setParameter("rg", rg);	
		}
		
		if(telefone != null) {
			typedQuery.setParameter("celular", telefone);
			typedQuery.setParameter("fixo", telefone);
		}
		
		return typedQuery.getResultList();
	}
	
	
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao){
		
		// Retorna uma lista de alunos de acordo com a situcao selecionada por ordem alfabetica de nome crescente
		return entityManager.createQuery("SELECT a FROM Aluno a WHERE a.situacao = :situacao ORDER BY a.nome" , Aluno.class)
				.setParameter("situacao", situacao)
				.getResultList();
	}
	
	
	
	public List<Acesso> listAcessosAlunos(String matricula , LocalDate dataInicial , LocalDate dataFinal){
		
		StringBuilder jpql = new StringBuilder("SELECT a FROM Acesso a WHERE ");
		
		// Se existir uma matricula na busca
		if(!StringUtils.isEmpty(matricula)) {
			jpql.append("a.aluno.matricula = :matricula AND ");
		}
		
		if(dataInicial != null) {
			jpql.append("a.entrada >= :entradaInicio AND "); 
		}
		
		if(dataFinal != null) {
			jpql.append("a.saida <= :saidaFim AND "); 
		}
		
		jpql.append("1 = 1 ORDER BY a.entrada"); // Para finalizar a query e necessario uma condição verdadeira
		
		/************************************************************************************************************************************************************************/
		TypedQuery<Acesso> typedQuery = entityManager.createQuery(jpql.toString() , Acesso.class); // Query criada
		
		if(!StringUtils.isEmpty(matricula)) {
			typedQuery.setParameter("matricula", matricula);
		}
		
		if(dataInicial != null) {
			LocalDateTime ldt = LocalDateTime.of(dataInicial, LocalTime.of(0, 0, 0)); // LocalTime que é a Hora foi iniciado em 00:00hs
			typedQuery.setParameter("entradaInicio", ldt);
		
		}
		
		if(dataFinal != null) {
			LocalDateTime ldt = LocalDateTime.of(dataFinal, LocalTime.of(23, 59, 59)); // LocalTime que é a Hora foi iniciado em 00:00hs
			typedQuery.setParameter("saidaFim", ldt);
		}
		
		return typedQuery.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
