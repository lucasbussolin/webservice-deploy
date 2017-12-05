package rest.pc;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/pc")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@RequestScoped
public class PcService {

	@GET
	@Path("/adicionar/{tipoComponente}/{componente}")
	public String adicionar(@PathParam("tipoComponente") String nome,
			@PathParam("componente") String componente) {
		String retorno = null;
		
		return retorno ;
	}
}
