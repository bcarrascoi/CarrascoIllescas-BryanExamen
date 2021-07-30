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
import ec.edu.ups.entidad.Cliente;

@Path("/cliente/")
public class ClienteRest {

	@EJB
	private ClienteFacade ejClienteFacade;
	private Cliente clientes;
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public Response registrarCliente(@FormParam("cedula") String cedula,
									@FormParam("nombre") String nombre,
									@FormParam("apellido") String apellido,
									@FormParam("correo") String correo,
									@FormParam("direccion")String direccion,
									@FormParam("telefono") String telefono) throws IOException{
			Cliente cli = new Cliente();
			cli.setApellido(apellido);
			cli.setCedula(cedula);
			cli.setCorreo(correo);
			cli.setDireccion(direccion);
			cli.setNombre(nombre);
			cli.setTelefono(telefono);
	
			try {
				ejClienteFacade.create(cli);
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error al crear el cliente");
				return Response.status(500).build();
			}
			return Response.ok("Cliente creado correctamente" + cli).build();
	}
	
	@GET
	@Path("{cedula}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getIdEmp(@PathParam("cedula") String cedula) {
		Jsonb jsonb = JsonbBuilder.create();
		Cliente cliente = new Cliente();
		try {
			cliente = ejClienteFacade.buscarCli(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return Response.ok(jsonb.toJson(cliente)).build();
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
