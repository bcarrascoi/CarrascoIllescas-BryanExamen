package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.entidad.Cliente;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ClienteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteFacade ejClienteFacade;
	
	private List<Cliente> lista;
	private Cliente clientes;
	
	public int codigoCliente;
	public String cedula;
	public String nombre;
	public String apellido;
	public String correo;
	public String direccion;
	public String telefono;
	
	public ClienteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		clientes = new Cliente();
		lista = new ArrayList<Cliente>();
	}
	
	public Cliente[] getLista() {
		return lista.toArray(new Cliente[0]);
	}
	
	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}
	

	public ClienteFacade getEjClienteFacade() {
		return ejClienteFacade;
	}

	public void setEjClienteFacade(ClienteFacade ejClienteFacade) {
		this.ejClienteFacade = ejClienteFacade;
	}

	public Cliente getClientes() {
		return clientes;
	}

	public void setClientes(Cliente clientes) {
		this.clientes = clientes;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String registrarCliente() {
		System.out.println("Nombre:" + this.nombre + "cedula: " +this.cedula);
		
		try {
			Cliente cli = new Cliente();
			cli.setCodigoCliente(0);
			cli.setCedula(this.cedula);
			cli.setNombre(this.nombre);
			cli.setApellido(this.apellido);
			cli.setCorreo(this.correo);
			cli.setDireccion(this.direccion);
			cli.setTelefono(this.telefono);
			ejClienteFacade.create(cli);
			lista = ejClienteFacade.findAll();
			cli = new Cliente();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al registrar");
		}
		return "";
	}
	
	public String buscarCliente() {
		System.out.println("La cedula ingresada es: "+ this.cedula);
		lista = ejClienteFacade.buscarCliC(cedula);
		return null;
	}
	
}
