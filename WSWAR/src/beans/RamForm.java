package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import pce.ejb.PcEjb;

@ManagedBean(name="RamForm")
public class RamForm {
	@EJB
	private PcEjb ejb;
	
	private String nome;
	private String fabricante;
	private String slot;
	
	public void adicionaComponente() {
		if (nome != null && fabricante != null && slot != null) {
			ejb.adicionaRAM(nome, fabricante, slot);
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
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	
	
}
