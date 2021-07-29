package ec.edu.ups.rest;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.entidad.Reserva;

@Path("/reserva/")
public class ReservaRest {

	@EJB
	private ReservaFacade ejReservaFacade;
	private Reserva reserva;
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public Response registrarReserva(@FormParam("numPersonas") Integer numPersonas,
									@FormParam("fechaReserva") String fechaReserva,
									@FormParam("horaReserva") String horaReserva
									) throws IOException{
		
		Reserva res = new Reserva();
		res.setNumPersonas(numPersonas);
		res.setFechaReserva(fechaReserva);
		res.setHoraReserva(horaReserva);
		try {
			ejReservaFacade.create(res);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear la reserva");
			return Response.status(500).build();
		}
		return Response.ok("OK reserva"+res).build();
	}
}
