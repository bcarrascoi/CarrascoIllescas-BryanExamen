package ec.edu.ups.rest;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
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

@Path("/reservas/")
public class RestReserva {

	@EJB
	private ClienteFacade ejClienteFacade;
	private RestauranteFacade ejRestauranteFacade;
	private ReservaFacade ejReservaFacade;
	private Cliente cliente;
	private Restaurante restaurante;

	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrarReserva(
								@FormParam("cedula") String cedula,
								@FormParam("numPersonas") Integer numPersonas,
								@FormParam("fechaReserva") String fechaReserva,
								@FormParam("horaReserva") String horaReserva,
								@FormParam("nombreRest") String nombreRest
			) throws IOException {
		
		cliente = ejClienteFacade.buscarCli(cedula);
		if (cliente != null) {
			try {
				restaurante = ejRestauranteFacade.buscarR(nombreRest);
				if (numPersonas <= restaurante.getNumAforo()) {
					Reserva rese = new Reserva();
					rese.setNumPersonas(numPersonas);
					rese.setFechaReserva(fechaReserva);
					rese.setHoraReserva(horaReserva);
					rese.setCliente(cliente);
					rese.setRestaurante(restaurante);
						ejReservaFacade.create(rese);	
					return Response.ok("Reserva creada OK").build();
				}else {
					return Response.ok("No se realizó la reserva por exceder el número de aforo").build();
				}

			} catch (Exception e) {
				e.printStackTrace();
				return Response.status(500).build();
			}
		} else {
			return Response.ok("La cedula no existe").build();
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

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllClientes() {
		System.out.println(" ok");
		Jsonb jsonb = JsonbBuilder.create();
		List<Cliente> clientes = ejClienteFacade.findAll();
		return Response.ok(jsonb.toJson(clientes)).build();
	}
}
