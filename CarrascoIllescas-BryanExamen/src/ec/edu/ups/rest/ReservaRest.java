package ec.edu.ups.rest;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidad.Cliente;
import ec.edu.ups.entidad.Reserva;
import ec.edu.ups.entidad.Restaurante;

@Path("/reserva/")
public class ReservaRest {

	@EJB
	private ReservaFacade ejReservaFacade;
	private ClienteFacade ejClienteFacade;
	private RestauranteFacade ejRestauranteFacade;
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrarReserva(String jsonRe) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		Reserva reserva = jsonb.fromJson(jsonRe, Reserva.class);

		Cliente cli = ejClienteFacade.findCli(reserva.getCliente().getCodigoCliente());
		if (cli != null) {
			try {
				Reserva reser = new Reserva();
				reser.setNumPersonas(reserva.getNumPersonas());
				reser.setFechaReserva(reserva.getFechaReserva());
				reser.setHoraReserva(reserva.getHoraReserva());
				reser.setCliente(cli);

				Restaurante rest = ejRestauranteFacade.buscarR(reser.getRestaurante().getNombreRest());
				reser.setRestaurante(rest);
				ejReservaFacade.create(reser);
				return Response.ok("Reserva OK ").build();

			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).build();
			}
		} else {
			return Response.ok("Cedula no OK").build();
		}

	}
	@GET
	@Path("{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIdEmp(@PathParam("cedula") String cedula) {
		Jsonb jsonb = JsonbBuilder.create();
		Reserva reserva = new Reserva();
		try {
			reserva = ejReservaFacade.buscarCedulaCli(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return Response.ok(jsonb.toJson(reserva)).build();
	}
	
}