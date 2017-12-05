package vo.personalcomputer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import viewobject.personalcomputer.Cpu;
import viewobject.personalcomputer.Gpu;
import viewobject.personalcomputer.PlacaMae;
import viewobject.personalcomputer.Psu;
import viewobject.personalcomputer.Ram;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType
public class Computador {
	@XmlElement(name = "ValorTotal")
	private Double valorTotal = 0.00;
	
	@XmlElement(name = "PSU")
	private Psu fonte;
	
	@XmlElement(name = "Motherboard")
	private PlacaMae placaMae;
	
	@XmlElement(name = "Ram")
	private Ram ram;
	
	@XmlElement(name = "CPU")
	private Cpu cpu;
	
	@XmlElement(name = "GPU")
	private Gpu gpu;

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Psu getFonte() {
		return fonte;
	}

	public void setFonte(Psu fonte) {
		this.fonte = fonte;
	}

	public PlacaMae getPlacaMae() {
		return placaMae;
	}

	public void setPlacaMae(PlacaMae placaMae) {
		this.placaMae = placaMae;
	}

	public Ram getRam() {
		return ram;
	}

	public void setRam(Ram ram) {
		this.ram = ram;
	}

	public Cpu getCpu() {
		return cpu;
	}

	public void setCpu(Cpu cpu) {
		this.cpu = cpu;
	}

	public Gpu getGpu() {
		return gpu;
	}

	public void setGpu(Gpu gpu) {
		this.gpu = gpu;
	}
	
	

}
