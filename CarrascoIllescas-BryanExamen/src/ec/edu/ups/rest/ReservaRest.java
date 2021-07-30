package ec.edu.ups.rest;

import java.io.IOException;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

	private Cliente cliente;
	private Restaurante restaurante;

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public Response registrarReserva(String jsonReserva) throws IOException {
		Jsonb jsonb = JsonbBuilder.create();
		Reserva reserva = jsonb.fromJson(jsonReserva, Reserva.class);

		cliente = ejClienteFacade.buscarCli(cliente.getCedula());
		if (cliente != null) {
			restaurante = ejRestauranteFacade.buscarR(restaurante.getNombreRest());
			try {
				if (reserva.getNumPersonas() <= restaurante.getNumAforo()) {
					Reserva rese = new Reserva();
					rese.setNumPersonas(reserva.getNumPersonas());
					rese.setFechaReserva(reserva.getFechaReserva());
					rese.setHoraReserva(reserva.getHoraReserva());
					rese.setCliente(cliente);

					ejReservaFacade.create(rese);
					return Response.ok("Reserva creada OK").build();
				}else {
					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).build();
			}
		} else {
			return Response.ok("La cedula no existe").build();
		}

	}

}