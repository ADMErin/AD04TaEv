package entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="conductor")
public class Conductor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="vehiculo")
	private String vehiculo;
	
	@OneToMany (mappedBy="conductor", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	//se excluye CascadeType.REMOVE
	List <Viaje> viajes = new ArrayList<>();

	public Conductor() {}
	/**
	 * @param nombre
	 * @param vehiculo
	 */
	public Conductor(String nombre, String vehiculo) {
		this.nombre = nombre;
		this.vehiculo = vehiculo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		return "ConductorEntidad [id=" + id + ", nombre=" + nombre + ", vehiculo=" + vehiculo + "]";
	}
	
}
