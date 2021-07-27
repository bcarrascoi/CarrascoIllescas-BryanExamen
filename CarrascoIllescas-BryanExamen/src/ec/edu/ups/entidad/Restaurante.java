package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurante implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonbProperty
	private int codigoRest;
	@JsonbProperty
	private String nombreRest;
	@JsonbProperty
	private String direccion;
	@JsonbProperty
	private String telefono;
	@JsonbProperty
	private int numAforo;

	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurante")
	private ArrayList<Reserva> reservas;

	public Restaurante() {
		// TODO Auto-generated constructor stub
	}

	public Restaurante(int codigoRest, String nombreRest, String direccion, String telefono, int numAforo,
			ArrayList<Reserva> reservas) {
		super();
		this.codigoRest = codigoRest;
		this.nombreRest = nombreRest;
		this.direccion = direccion;
		this.telefono = telefono;
		this.numAforo = numAforo;
		this.reservas = reservas;
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

	@Override
	public String toString() {
		return "Restaurante [codigoRest=" + codigoRest + ", nombreRest=" + nombreRest + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", numAforo=" + numAforo + ", reservas=" + reservas + "]";
	}

}
