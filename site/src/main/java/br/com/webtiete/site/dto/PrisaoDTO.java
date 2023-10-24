package br.com.webtiete.site.dto;

import java.util.Arrays;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "prisao")
public class PrisaoDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String nome;
	private String cpf;
	private String servicos;
	private double valor;
	private String crimes;
	private String policial;

	public PrisaoDTO() {
	}

	public PrisaoDTO(String id, String nome, String cpf, String servicos, double valor, String crimes,
			String policial) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.servicos = servicos;
		this.valor = valor;
		this.crimes = crimes;
		this.policial = policial;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getServicos() {
		return servicos;
	}

	public void setServicos(String servicos) {
		this.servicos = servicos;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getCrimes() {
		return crimes;
	}

	public void setCrimes(String crimes) {
		this.crimes = crimes;
	}

	public String getPolicial() {
		return policial;
	}

	public void setPolicial(String policial) {
		this.policial = policial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, crimes, id, nome, policial, servicos, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrisaoDTO other = (PrisaoDTO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(crimes, other.crimes) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(policial, other.policial)
				&& Objects.equals(servicos, other.servicos)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}

}
