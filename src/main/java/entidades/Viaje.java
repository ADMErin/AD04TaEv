package entidades;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="viaje")
public class Viaje {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="destino")
	private String destino;
	
	@Column(name="origen")
	private String origen;
	
	@Column(name="fechaHora")
	private Date fechaHora;
	
	@Column(name="plazas")
	private int plazas;
	
	@ManyToOne
	@JoinColumn (name = "conductor_id")
	private Conductor conductor;
	
	@OneToMany (mappedBy="viaje", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	//se excluye CascadeType.REMOVE
	List <Reserva> reservas = new ArrayList<>();
	
	public Viaje() {}

	/**
	 * @param destino
	 * @param origen
	 * @param fechaHora
	 * @param plazas
	 * @param conductorId
	 */
	public Viaje(String destino, String origen, Date fechaHora, int plazas, Conductor conductorId) {
		this.destino = destino;
		this.origen = origen;
		this.fechaHora = fechaHora;
		this.plazas = plazas;
		this.conductor = conductorId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getPlazas() {
		return plazas;
	}

	public void setPlazas(int plazas) {
		this.plazas = plazas;
	}

	@Override
	public String toString() {
		return "Viaje [id=" + id + ", destino=" + destino + ", origen=" + origen + ", fechaHora=" + fechaHora
				+ ", plazas=" + plazas + ", conductor=" + conductor + ", reservas=" + reservas + "]";
	}


	
	
}