package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import pce.ejb.PcEjb;

@ManagedBean(name="PsuForm")
public class PsuForm {

	@EJB
	private PcEjb ejb;
	
	private String nome;
	private String fabricante;
	private Integer potencia;
	
	public void adicionaComponente() {
		if (nome != null && fabricante != null && potencia != null) {
			ejb.adicionaPsu(nome, fabricante, potencia);
		}
	}
	
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
}
