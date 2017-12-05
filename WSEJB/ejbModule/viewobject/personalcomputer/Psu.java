package viewobject.personalcomputer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Psu {
	@XmlElement(name = "Nome")
	private String nome;

	@XmlElement(name = "Fabricante")
	private String fabricante;

	@XmlElement(name = "Potencia")
	private Integer potencia;
	
	@XmlElement(name = "Classificacao")
	private String classificacao;

	@XmlElement(name = "Valor")
	private Double valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Integer getPotencia() {
		return potencia;
	}

	public void setPotencia(Integer potencia) {
		this.potencia = potencia;
	}

	public String getClassficacao() {
		return classificacao;
	}

	public void setClassficacao(String classficacao) {
		this.classificacao = classficacao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
