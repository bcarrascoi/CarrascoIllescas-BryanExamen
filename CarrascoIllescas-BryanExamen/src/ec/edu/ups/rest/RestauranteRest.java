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

import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidad.Cliente;
import ec.edu.ups.entidad.Restaurante;

@Path("/restaurante/")
public class RestauranteRest {

	@EJB
	private RestauranteFacade ejRestauranteFacade;
	private Restaurante restaurantes;
	
	@POST
	@Path("registrar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrarRestaurante(@FormParam("nombreRest") String nombreRest,
										@FormParam("direccion") String direccion,
										@FormParam("telefono") String telefono,
										@FormParam("numAforo") Integer numAforo
			) throws IOException {
		Restaurante rest = new Restaurante();
		rest.setNombreRest(nombreRest);
		rest.setDireccion(direccion);
		rest.setTelefono(telefono);
		rest.setNumAforo(numAforo.intValue());
		try {
			ejRestauranteFacade.create(rest);
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al crear el restaurante");
			return Response.status(500).build();
		}
		return Response.ok("Restaurante OK"+rest).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIdEmp(@PathParam("codigoRest") Integer id) {
		System.out.println("ENtra a api call");
		System.out.println("Id del api="+id);
		Jsonb jsonb = JsonbBuilder.create();
		Restaurante rest = new Restaurante();
		try {
			rest = ejRestauranteFacade.find(id);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return Response.ok(jsonb.toJson(rest)).build();
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRestaurantes() {
		System.out.println("ok");
		Jsonb jsonb = JsonbBuilder.create();
		List<Restaurante> restaurantes = ejRestauranteFacade.findAll();
		return Response.ok(jsonb.toJson(restaurantes)).build();
	}
	
}
