package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import pce.ejb.PcEjb;

@ManagedBean(name="GpuForm")
public class GpuForm {
	@EJB
	private PcEjb ejb;
	
	private String nome;
	private String fabricante;
	
	public void adicionaComponente() {
		if (nome != null && fabricante != null) {
			ejb.adicionaGPU(nome, fabricante);
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
	
}
