package ec.edu.ups.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.annotation.FacesConfig;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import ec.edu.ups.ejb.ClienteFacade;
import ec.edu.ups.ejb.ReservaFacade;
import ec.edu.ups.entidad.Reserva;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ReservaBean  implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private ReservaFacade ejReservaFacade;
	private ClienteFacade ejClienteFacade;
	
	private List<Reserva>lista;
	private Reserva reservas;
	
	private int codigoReserva;
	private int numPersonas;
	private String fechaReserva;
	private String horaReserva;
	
	public ReservaBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init() {
		reservas = new Reserva();
		lista = new ArrayList<Reserva>();
	}
	
	public Reserva[] getLista() {
		return lista.toArray(new Reserva[0]);
	}
	
	public void serLista(List<Reserva> lista) {
		this.lista = lista;
	}

	public ReservaFacade getEjReservaFacade() {
		return ejReservaFacade;
	}

	public void setEjReservaFacade(ReservaFacade ejReservaFacade) {
		this.ejReservaFacade = ejReservaFacade;
	}

	public ClienteFacade getEjClienteFacade() {
		return ejClienteFacade;
	}
	public void setEjClienteFacade(ClienteFacade ejClienteFacade) {
		this.ejClienteFacade = ejClienteFacade;
	}
	
	public Reserva getReservas() {
		return reservas;
	}

	public void setReservas(Reserva reservas) {
		this.reservas = reservas;
	}

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public int getNumPersonas() {
		return numPersonas;
	}

	public void setNumPersonas(int numPersonas) {
		this.numPersonas = numPersonas;
	}

	public String getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(String fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public void setLista(List<Reserva> lista) {
		this.lista = lista;
	}
	
	
	public String addReserva() {
		
		try {
			Reserva res = new Reserva();
			res.setCodigoReserva(0);
			res.setNumPersonas(this.numPersonas);
			res.setFechaReserva(this.fechaReserva);
			res.setHoraReserva(this.horaReserva);
			ejReservaFacade.create(res);
			lista = ejReservaFacade.findAll();
			res = new Reserva();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error al registrar");
		}
		return "index.html";
	}
	
	
	
	
}
