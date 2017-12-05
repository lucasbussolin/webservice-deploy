package viewobject.personalcomputer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class PlacaMae {
	@XmlElement(name = "Nome")
	private String nome;

	@XmlElement(name = "Fabricante")
	private String fabricante;

	@XmlElement(name = "Socket")
	private String socket;

	@XmlElement(name = "SlotRam")
	private String slotRam;

	@XmlElement(name = "TotalRam")
	private Integer totalRam;

	@XmlElement(name = "QuantidadePcie")
	private Integer qtdPcie;

	@XmlElement(name = "Valor")
	private Double valor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
