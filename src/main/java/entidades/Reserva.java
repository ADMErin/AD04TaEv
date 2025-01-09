package entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reservas")
public class Reserva {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="fechaReserva")
	private Date fechaReserva;
	
	@Column(name="numPlazasReservadas")
	private int numPlazas;
	
	@ManyToOne
	@JoinColumn(name="pasajero_id")
	private Pasajero pasajeroId;
	
	@ManyToOne
	@JoinColumn(name="viaje_id")
	private Viaje viajeId;
	
	public Reserva() {}

	/**
	 * @param fechaReserva
	 * @param numPlazas
	 * @param pasajeroId
	 * @param viajeId
	 */
	public Reserva(Date fechaReserva, int numPlazas, Pasajero pasajeroId, Viaje viajeId) {
		this.fechaReserva = fechaReserva;
		this.numPlazas = numPlazas;
		this.pasajeroId = pasajeroId;
		this.viajeId = viajeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public Pasajero getPasajeroId() {
		return pasajeroId;
	}

	public void setPasajeroId(Pasajero pasajeroId) {
		this.pasajeroId = pasajeroId;
	}

	public Viaje getViajeId() {
		return viajeId;
	}

	public void setViajeId(Viaje viajeId) {
		this.viajeId = viajeId;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaReserva=" + fechaReserva + ", numPlazas=" + numPlazas + ", pasajeroId="
				+ pasajeroId + ", viajeId=" + viajeId + "]";
	}
	
	

}
