package br.com.webtiete.site.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "edital")
public class Edital {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(name = "instituicao", nullable = false)
	private String instituicao;
	@Column(name = "organizacao", nullable = false)
	private String organizacao;
	@Column(name = "cargoo", nullable = false)
	private String cargoo;
	@Column(name = "policialCadastrou", nullable = false)
	private String policialCadastrou;
	@Column(name = "data", nullable = false)
	private String data;
	@Column(name = "diaNome", nullable = false)
	private String diaNome;
	@Column(name = "hora", nullable = false)
	private String hora;
	@Column(name = "estudo", nullable = false)
	private String estudo;

	public Edital() {
		super();
	}

	public Edital(String id, String instituicao, String organizacao, String cargoo, String policialCadastrou,
			String data, String diaNome, String hora, String estudo) {
		super();
		this.id = id;
		this.instituicao = instituicao;
		this.organizacao = organizacao;
		this.cargoo = cargoo;
		this.policialCadastrou = policialCadastrou;
		this.data = data;
		this.diaNome = diaNome;
		this.hora = hora;
		this.estudo = estudo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(String organizacao) {
		this.organizacao = organizacao;
	}

	public String getCargoo() {
		return cargoo;
	}

	public void setCargoo(String cargoo) {
		this.cargoo = cargoo;
	}

	public String getPolicialCadastrou() {
		return policialCadastrou;
	}

	public void setPolicialCadastrou(String policialCadastrou) {
		this.policialCadastrou = policialCadastrou;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDiaNome() {
		return diaNome;
	}

	public void setDiaNome(String diaNome) {
		this.diaNome = diaNome;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getEstudo() {
		return estudo;
	}

	public void setEstudo(String estudo) {
		this.estudo = estudo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cargoo, data, diaNome, estudo, hora, id, instituicao, organizacao, policialCadastrou);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edital other = (Edital) obj;
		return Objects.equals(cargoo, other.cargoo) && Objects.equals(data, other.data)
				&& Objects.equals(diaNome, other.diaNome) && Objects.equals(estudo, other.estudo)
				&& Objects.equals(hora, other.hora) && Objects.equals(id, other.id)
				&& Objects.equals(instituicao, other.instituicao) && Objects.equals(organizacao, other.organizacao)
				&& Objects.equals(policialCadastrou, other.policialCadastrou);
	}

}
