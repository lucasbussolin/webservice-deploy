package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "PegaPreco")
public class BeanForm {
	@ManagedProperty(value = "#{Resultado}")
	private ResultadoBean result;

	private Double preco;
	private Boolean resultado = false;

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public void calculaPc() {
		if (preco != null || preco < 1500) {
			resultado = true;
			result.montaPc(preco);
		}
	}

	public Boolean getResultado() {
		return resultado;
	}

	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}

	public ResultadoBean getResult() {
		return result;
	}

	public void setResult(ResultadoBean result) {
		this.result = result;
	}

}
