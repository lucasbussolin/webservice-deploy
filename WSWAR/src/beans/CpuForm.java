package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import pce.ejb.PcEjb;

@ManagedBean(name="CpuForm")
public class CpuForm {
	@EJB
	private PcEjb ejb;
	
	private String nome;
	private String fabricante;
	private String socket;
	
	public void adicionaComponente() {
		if (nome != null && fabricante != null && socket != null) {
			//Manda pro Ejb salvar
			ejb.adicionaCPU(nome, fabricante, socket);
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
	public String getSocket() {
		return socket;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	
	

}
