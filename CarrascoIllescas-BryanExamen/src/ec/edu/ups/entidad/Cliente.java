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
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonbProperty
	private int codigoCliente;
	@JsonbProperty
	private String cedula;
	@JsonbProperty
	private String nombre;
	@JsonbProperty
	private String apellido;
	@JsonbProperty
	private String correo;
	@JsonbProperty
	private String direccion;
	@JsonbProperty
	private String telefono;
	
	@JsonbTransient
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente" )
	private ArrayList<Reserva> reservas;
	
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(int codigoCliente, String cedula, String nombre, String apellido, String correo, String direccion,
			String telefono, ArrayList<Reserva> reservas) {
		super();
		this.codigoCliente = codigoCliente;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.reservas = reservas;
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

	@Override
	public String toString() {
		return "Cliente [codigoCliente=" + codigoCliente + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido="
				+ apellido + ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", reservas=" + reservas + "]";
	}


}
