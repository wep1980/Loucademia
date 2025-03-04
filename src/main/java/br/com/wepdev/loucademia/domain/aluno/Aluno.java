package br.com.wepdev.loucademia.domain.aluno;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.wepdev.loucademia.application.utils.StringUtils;

@Entity
@Table(name = "ALUNO")
public class Aluno implements Serializable{
	private static final long serialVersionUID = 1L;
	

	public enum Sexo {
		Masculino , Feminino;
	}
	
	public enum Situacao {
		Ativo, Inativo, Pendente;
	}
	
	@Id
	@Column(name = "ID", nullable = false, length = 8)
	private String matricula;
	
	@Column(name = "NOME", nullable = false, length = 64)
	private String nome;
	
	@Enumerated // Mapeamento para Enum
	@Column(name = "SEXO", nullable = false, length = 1)
	private Sexo sexo;
	
	@Column(name = "RG", nullable = false, length = 10)
	private Integer rg;
	
	@Column(name = "NASCIMENTO", nullable = false)
	private LocalDate dataNascimento;
	
	@Enumerated
	@Column(name = "SITUACAO", nullable = false, length = 1)
	private Situacao situacao;
	
	@Column(name = "EMAIL", nullable = true, length = 64)
	private String email;
	
	@Embedded // Avisa para JPA que o endereco faz parte da entidade aluno, v�o compartilhar o mesmo ID de aluno
	private Endereco endereco = new Endereco();
	
	@Embedded // Avisa para JPA que o telefone faz parte da entidade aluno, v�o compartilhar o mesmo ID de aluno
	private Telefone telefone = new Telefone();
	
	

	public void gerarMatricula(String maxMatricula) {
		Year ano = Year.now(); // Pegando o ano atual
		
		if(maxMatricula == null) {
		   maxMatricula = ano + StringUtils.colocaZerosAEsquerda(0, 4); // Para o primeiro numero de matricula sera o ano atual mais 0000 EXEMPLO = 20210000
		}
		
		int sequencial = Integer.parseInt(maxMatricula.substring(4)); // O indice come�a no 0, ent�o o 4 e o quinto elemento, ou seja pulamos os 4 primeiros, transformando a String em um numero inteiro
		sequencial++; // SE O NUMERO DE MATRICULA JA EXISTIR E SOMADO + 1 
		
		this.matricula = ano + StringUtils.colocaZerosAEsquerda(sequencial, 4); // RESULTADO FINAL � = 20210001
	}
	
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Sexo getSexo() {
		return sexo;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", rg=" + rg + ", dataNascimento=" + dataNascimento
				+ ", situacao=" + situacao + ", email=" + email + ", endereco=" + endereco + ", telefone=" + telefone
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	
	

}
