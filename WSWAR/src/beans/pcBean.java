package beans;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="PC")
public class pcBean {
	
	private String componente;

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		System.out.println(componente);
		this.componente = componente;
	}
	
	
	
}
