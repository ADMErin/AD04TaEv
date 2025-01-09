package entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="pasajero")
public class Pasajero {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="email")
	private String email;
	
	/*@OneToMany (mappedBy="pasajero", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	//se excluye CascadeType.REMOVE
	List <Reserva> reservas = new ArrayList<>();
	*/
	public Pasajero() {}

	/**
	 * @param nombre
	 * @param email
	 */
	public Pasajero(String nombre, String email) {
		this.nombre = nombre;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
	}
	
	
	
}