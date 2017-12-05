package beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pce.ejb.MontaPc;
import vo.personalcomputer.Computador;

@SessionScoped
@ManagedBean(name = "Resultado")
public class ResultadoBean {
	@EJB
	private MontaPc montapc;
	
	private Boolean pegou = false;
	
	private Computador comp;
	
	public Computador getComp() {
		return comp;
	}

	public void setComp(Computador comp) {
		this.comp = comp;
	}

	public void montaPc(Double preco) {
		comp = new Computador();
		comp = montapc.getGpuBuild(preco);
		pegou = true;
	}

	public MontaPc getMontapc() {
		return montapc;
	}

	public void setMontapc(MontaPc montapc) {
		this.montapc = montapc;
	}

	public Boolean getPegou() {
		return pegou;
	}

	public void setPegou(Boolean pegou) {
		this.pegou = pegou;
	}
	
	
	
}
