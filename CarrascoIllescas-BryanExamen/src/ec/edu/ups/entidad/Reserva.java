package ec.edu.ups.entidad;

import java.io.Serializable;
import java.util.Date;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reserva implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonbProperty
	private int codigoReserva;
	@JsonbProperty
	private int numPersonas;
	@JsonbProperty
	private String fechaReserva;
	@JsonbProperty
	private String horaReserva;

	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	@JsonbTransient
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "restaurante_id", nullable = false)
	@JsonbTransient
	private Restaurante restaurante;

	public Reserva() {
		// TODO Auto-generated constructor stub
	}

	public Reserva(int codigoReserva, int numPersonas, String fechaReserva, String horaReserva, Cliente cliente,
			Restaurante restaurante) {
		super();
		this.codigoReserva = codigoReserva;
		this.numPersonas = numPersonas;
		this.fechaReserva = fechaReserva;
		this.horaReserva = horaReserva;
		this.cliente = cliente;
		this.restaurante = restaurante;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	@Override
	public String toString() {
		return "Reserva [codigoReserva=" + codigoReserva + ", numPersonas=" + numPersonas + ", fechaReserva="
				+ fechaReserva + ", horaReserva=" + horaReserva + ", cliente=" + cliente + ", restaurante="
				+ restaurante + "]";
	}

}
