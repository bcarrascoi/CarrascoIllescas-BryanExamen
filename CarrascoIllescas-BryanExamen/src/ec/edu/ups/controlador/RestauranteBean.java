package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import ec.edu.ups.ejb.RestauranteFacade;
import ec.edu.ups.entidad.Restaurante;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class RestauranteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private RestauranteFacade ejRestauranteFacade;
	
	private List<Restaurante> listaR;
	private Restaurante restaurantes;
	
	private int codigoRest;
	private String nombreRest;
	private String direccion;
	private String telefono;
	private int numAforo;
	
	public RestauranteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		restaurantes = new Restaurante();
		listaR = new ArrayList<Restaurante>();
	}

	public Restaurante[] getListaR() {
		return listaR.toArray(new Restaurante[0]);
	}
	
	public void setListaR(List<Restaurante> listaR) {
		this.listaR = listaR;
	}

	public RestauranteFacade getEjRestauranteFacade() {
		return ejRestauranteFacade;
	}

	public void setEjRestauranteFacade(RestauranteFacade ejRestauranteFacade) {
		this.ejRestauranteFacade = ejRestauranteFacade;
	}

	public Restaurante getRestaurantes() {
		return restaurantes;
	}

	public void setRestaurantes(Restaurante restaurantes) {
		this.restaurantes = restaurantes;
	}

	public int getCodigoRest() {
		return codigoRest;
	}

	public void setCodigoRest(int codigoRest) {
		this.codigoRest = codigoRest;
	}

	public String getNombreRest() {
		return nombreRest;
	}

	public void setNombreRest(String nombreRest) {
		this.nombreRest = nombreRest;
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

	public int getNumAforo() {
		return numAforo;
	}

	public void setNumAforo(int numAforo) {
		this.numAforo = numAforo;
	}
	
	public String registrarRestaurante() {
		System.out.println("Nombre Restaurante: " + this.nombreRest);
		try {
			Restaurante resta = new Restaurante();
			resta.setCodigoRest(0);
			resta.setNombreRest(this.nombreRest);
			resta.setDireccion(this.direccion);
			resta.setTelefono(this.telefono);
			resta.setNumAforo(this.numAforo);
			ejRestauranteFacade.create(resta);
			listaR = ejRestauranteFacade.findAll();
			resta = new Restaurante();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al registrar");
		}
	return "";
	}
	
	public String buscarRestaurante(String nombreRest) {
		System.out.println("El nombre ingresado es: " + this.nombreRest);
		listaR = (List<Restaurante>) ejRestauranteFacade.buscarR(nombreRest);
		return null;
	}
	
	
	
}
