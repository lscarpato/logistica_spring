package br.com.walm.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class MalhaLogistica {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;
	private String pontoOrigem;
	private String pontoDestino;
	private BigDecimal distancia;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPontoOrigem() {
		return pontoOrigem;
	}

	public void setPontoOrigem(String pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
	}

	public String getPontoDestino() {
		return pontoDestino;
	}

	public void setPontoDestino(String pontoDestino) {
		this.pontoDestino = pontoDestino;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pontoDestino == null) ? 0 : pontoDestino.hashCode());
		result = prime * result
				+ ((pontoOrigem == null) ? 0 : pontoOrigem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MalhaLogistica)) {
			return false;
		}
		MalhaLogistica other = (MalhaLogistica) obj;
		if (pontoDestino == null) {
			if (other.pontoDestino != null) {
				return false;
			}
		} else if (!pontoDestino.equals(other.pontoDestino)) {
			return false;
		}
		if (pontoOrigem == null) {
			if (other.pontoOrigem != null) {
				return false;
			}
		} else if (!pontoOrigem.equals(other.pontoOrigem)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
