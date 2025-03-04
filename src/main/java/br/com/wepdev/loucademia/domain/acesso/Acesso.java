package br.com.wepdev.loucademia.domain.acesso;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.wepdev.loucademia.domain.aluno.Aluno;

@Entity
@Table(name = "ENTRADAS_SAIDAS")
public class Acesso implements Serializable{
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID" , nullable = false)
	private Integer id;
	
	
	@ManyToOne // Muitos acessos para um aluno
	@JoinColumn(name = "ALUNO_ID" , nullable = false) // O nome dessa coluna � ALUNO_ID que vai se ligar com o id da tabela ALUNO
	private Aluno aluno;
	
	@Column(name = "ENTRADA" , nullable = false)
	private LocalDateTime entrada;
	
	@Column(name = "SAIDA" , nullable = true)
	private LocalDateTime saida;
	
	
	
	/*
	 * Metodo que verifica se a entrada e saida est�o preenchidas
	 */
	public boolean isEntradaSaidaPreenchidas() {
		if(entrada != null && saida != null) {
			return true;
		}
		return false;
	}
	
	
	/*
	 * Metodo que coloca a data e hora de entrada ou saida do aluno
	 */
	public TipoAcesso registrarAcesso() {
		LocalDateTime dataEHoraAtual = LocalDateTime.now(); 
		TipoAcesso tipoAcesso;
		
		if(entrada == null) {
			entrada = dataEHoraAtual;
			tipoAcesso = TipoAcesso.Entrada;
			
		} else if(saida == null) {
			saida = dataEHoraAtual;
			tipoAcesso = TipoAcesso.Saida;
			
		} else {
			tipoAcesso = null;
		}
		return tipoAcesso;
	}
	
	
	/*
	 * Metodo que calcula o tempo que o aluno ficou na academia
	 * Metodo ja retorna no formato que e exibido na tela
	 */
	public String calcularDuracao() {
		
		if(entrada == null || saida == null) {
			return null;
		}
		
		Duration d = Duration.between(entrada, saida);
		return String.format("%02d:%02d", d.toHoursPart() , d.toMinutesPart()); // toHoursPart() e toHoursPart() -> so a partir do java 9 em diante
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	@Override
	public String toString() {
		return "Acesso [id=" + id + ", aluno=" + aluno + ", entrada=" + entrada + ", saida=" + saida + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Acesso other = (Acesso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
