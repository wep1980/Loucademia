package br.com.wepdev.loucademia.domain.aluno;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class EstadoRepository {

	@PersistenceContext // Injecção de dependencia CDi
	private EntityManager entityManager; // Porta de entrada para JPA
	
	
	
	 public List<Estado> listEstados(){
		 
		// JPQL -> e = Alias , Estado.class -> Tipo de informação que sera retornado , Retorna um objeto do tipo TypedQuery<Estado>
		 TypedQuery<Estado> typedQuery = entityManager.createQuery("SELECT e FROM Estado e ORDER BY e.nome" , Estado.class); 
		 return typedQuery.getResultList(); // Retorna a lista de estados
		 
		 // FORMA MAIS SIMPLES DE IMPLEMENTAÇÃO DO METODO
		 //return entityManager.createQuery("SELECT e FROM Estado e ORDER BY e.nome" , Estado.class).getResultList();
	 }

}
