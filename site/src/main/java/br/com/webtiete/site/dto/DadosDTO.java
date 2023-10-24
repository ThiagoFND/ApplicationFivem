package br.com.webtiete.site.dto;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dados")
public class DadosDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "artigo", nullable = false)
	private String artigo;
	@Column(name = "crime", nullable = false)
	private String crime;
	@Column(name = "servicos", nullable = false)
	private String servicos;
	@Column(name = "valor", nullable = false)
	private String valor;

	public DadosDTO() {
	}

	public DadosDTO(String id, String artigo, String crime, String servicos, String valor) {
		super();
		this.id = id;
		this.artigo = artigo;
		this.crime = crime;
		this.servicos = servicos;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArtigo() {
		return artigo;
	}

	public void setArtigo(String artigo) {
		this.artigo = artigo;
	}

	public String getCrime() {
		return crime;
	}

	public void setCrime(String crime) {
		this.crime = crime;
	}

	public String getServicos() {
		return servicos;
	}

	public void setServicos(String servicos) {
		this.servicos = servicos;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(artigo, crime, id, servicos, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosDTO other = (DadosDTO) obj;
		return Objects.equals(artigo, other.artigo) && Objects.equals(crime, other.crime)
				&& Objects.equals(id, other.id) && Objects.equals(servicos, other.servicos)
				&& Objects.equals(valor, other.valor);
	}

}
