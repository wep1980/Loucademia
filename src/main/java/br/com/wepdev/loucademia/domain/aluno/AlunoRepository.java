package br.com.wepdev.loucademia.domain.aluno;

import java.time.Year;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.com.wepdev.loucademia.application.utils.StringUtils;

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
	
	
	/*
	 * Para excluir um objeto primeiro ele precisa ser carregado
	 */
	public void delete(String matricula) {
		Aluno aluno = findByMatricula(matricula); // CARREGA O ALUNO
		
		if(aluno != null) { // SE O ALUNO EXISTIR E REMOVIDO
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
		
		jpql.append("1 = 1"); // No final do JPQL teremos um AND, e para retirar ele 
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
