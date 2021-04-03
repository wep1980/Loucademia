package br.com.wepdev.loucademia.domain.acesso;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import br.com.wepdev.loucademia.domain.aluno.Aluno;

@Stateless
public class AcessoRepository {


	@PersistenceContext
	private EntityManager entityManager;
	
	
	/*
	 * Metodo que verifica qual foi o ultimo acesso do aluno
	 */
	public Acesso findUltimoAcesso(Aluno aluno) {
		
		/*
		 * Esse select acessa a matricula do aluno atraves da classe Acesso que possui uma variavel aluno e ordena por ordem descrecente, e assim pega o ultimo acesso do aluno.
		 * .setMaxResults(1) -> Metodo que permite pegar quantos registros no maximo ele vai trazer, nesse caso apenas 1
		 */
		try {
			return entityManager.createQuery("SELECT a FROM Acesso a WHERE a.aluno.matricula = :matricula ORDER BY a.id DESC" , Acesso.class)
					.setParameter("matricula", aluno.getMatricula())
					.setMaxResults(1)
					.getSingleResult();
		} catch (NoResultException e) { // Quando a JPA n�o encontra um registro ela lan�a uma excess�o do tipo NoResultException, Evita a excess�o de uma matricula que n�o exista
			return null; //Evita a excess�o de uma matricula que n�o exista
		}
	}
	
	
	public void cadastrarNovoAcesso(Acesso acesso) {
		entityManager.persist(acesso);
	}

}
