package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import pce.ejb.PcEjb;

@ManagedBean(name="MoboForm")
public class MoboForm {
	@EJB
	private PcEjb ejb;
	
	private String nome;
	private String fabricante;
	private String socket;
	private String slotRam;
	private Integer totalRam;
	private Integer qtdPcie;
	
	public void adicionaComponente() {
		if (nome != null && fabricante != null && socket != null && slotRam != null &&
				totalRam != null && qtdPcie != null) { 
			ejb.adicionaMobo(nome, fabricante, socket, slotRam, totalRam, qtdPcie);
		} 
	}
	
	
	public PcEjb getEjb() {
		return ejb;
	}
	public void setEjb(PcEjb ejb) {
		this.ejb = ejb;
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
	public String getSlotRam() {
		return slotRam;
	}
	public void setSlotRam(String slotRam) {
		this.slotRam = slotRam;
	}
	public Integer getTotalRam() {
		return totalRam;
	}
	public void setTotalRam(Integer totalRam) {
		this.totalRam = totalRam;
	}
	public Integer getQtdPcie() {
		return qtdPcie;
	}
	public void setQtdPcie(Integer qtdPcie) {
		this.qtdPcie = qtdPcie;
	}

	
}
