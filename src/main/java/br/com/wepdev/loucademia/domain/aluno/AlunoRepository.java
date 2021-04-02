package br.com.wepdev.loucademia.domain.aluno;

import java.time.Year;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
