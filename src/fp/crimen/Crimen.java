package fp.crimen;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import fp.utiles.Checkers;

public class Crimen implements Comparable<Crimen> {
	
	private LocalDateTime fecha;
	private String categoria;
	private List<String> descripcion;
	private DiaSemana diaSemana;
	private String distrito;
	private Resolucion resolucion;
	private String direccion;
	private Coordenadas coordenadas;
	
	/**
	 * @param fecha Indica la fecha y la hora del crimen. 
	 * @param categoria Indica el tipo de delito cometido.
	 * @param descripcion Lista con ciertas especificaciones que describen mejor el crimen.
	 * @param diaSemana Día de la semana en el que se cometió el crimen.
	 * @param distrito Distrito de la ciudad desconocida en el que se cometió el crimen.
	 * @param resolucion Resolución del delito.
	 * @param direccion Dirección en la que se cometió el delito.
	 * @param latitud Latitud de la localización exacta del crimen.
	 * @param longitud Longitud de la localización exacta del crimen.
	 * @throws IllegalArgumentException si la fecha es null
	 * @throws IllegalArgumentException si la fecha es posterior al dia actual
	 * @throws IllegalArgumentException si la categoria es null
	 * @throws IllegalArgumentException si la resolucion es null
	 */
	public Crimen(LocalDateTime fecha, String categoria, List<String> descripcion, DiaSemana diaSemana, 
			String distrito, Resolucion resolucion, String direccion, Double latitud, Double longitud) {
		Checkers.check("Error en la fecha", !(fecha.isAfter(LocalDateTime.now())) && fecha != null);
		this.fecha = fecha;
		Checkers.check("Error en la categoria", categoria!=null);
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.diaSemana = diaSemana;
		this.distrito = distrito;
		Checkers.check("Error en la resolucion", resolucion!=null);
		this.resolucion = resolucion;
		this.direccion = direccion;
		this.coordenadas = new Coordenadas(latitud, longitud);
	}
	
	/**
	 * @param fecha Indica la fecha y la hora del crimen. 
	 * @param categoria Indica el tipo de delito cometido.
	 * @param resolucion Resolución del delito.
	 * @throws IllegalArgumentException si la fecha es null
	 * @throws IllegalArgumentException si la fecha es posterior al dia actual
	 * @throws IllegalArgumentException si la categoria es null
	 * @throws IllegalArgumentException si la resolucion es null
	 */
	public Crimen(LocalDateTime fecha, String categoria, Resolucion resolucion) {
		Checkers.check("Error en la fecha", !(fecha.isAfter(LocalDateTime.now())) && fecha != null);
		this.fecha = fecha;
		Checkers.check("Error en la categoria", categoria!=null);
		this.categoria = categoria;
		this.descripcion = null;
		this.diaSemana = null;
		this.distrito = null;
		Checkers.check("Error en la resolucion", resolucion!=null);
		this.resolucion = resolucion;
		this.direccion = null;
		this.coordenadas = null;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	public String getCategoria() {
		return categoria;
	}
	public List<String> getDesc() {
		return descripcion;
	}
	public DiaSemana getDiaSem() {
		return diaSemana;
	}
	public String getDistrito() {
		return distrito;
	}
	public Resolucion getResolucion() {
		return resolucion;
	}
	public String getDireccion() {
		return direccion;
	}
	public Coordenadas getCoordenadas() {
		return coordenadas;
	}
	public Boolean getResuelto() {
		Boolean res = true;
		if (resolucion == Resolucion.SIN_RESOLUCION) {
			res = false;
		}
		return res;
	}
	public Integer getPrioridad() {
		int res = 0;
		if (categoria.contains("ASSAULT")) {
			res += 80;
		}
		else if (categoria.contains("WARRANT")) {
			res += 65;
		}
		else if (categoria.contains("THEFT") || categoria.contains("BURGLARY") || categoria.contains("ROBBERY")) {
			res += 50;
		}
		else if (categoria.contains("FRAUD") || categoria.contains("OFFENSE")) {
			res += 35;
		}
		else {
			res += 20;
		}
		if (resolucion == Resolucion.SIN_RESOLUCION) {
			res += 30;
		}
		return res;
	}

	public String toString() {
		return "Crimen: fecha=" + getFecha() + ", categoria=" + getCategoria() + ", descripcion="
				+ getDesc() + ", diaSemana=" + getDiaSem() + ", distrito=" + getDistrito() +
				", resolucion=" + getResolucion() + ", direccion=" + getDireccion() + ", coordenadas="
				+ getCoordenadas() + ", resuelto=" + getResuelto() + ", prioridad="
				+ getPrioridad();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(categoria, descripcion, fecha, resolucion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Crimen other = (Crimen) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(descripcion, other.descripcion)
				&& Objects.equals(fecha, other.fecha) && Objects.equals(resolucion, other.resolucion);
	}
	
	/**
	 * Los crimenes se ordenan por prioridad y distrito
	 */
	public int compareTo(Crimen c) {
		
		int res = getPrioridad().compareTo(c.getPrioridad());
		if (res==0) {
			res = getDistrito().compareTo(c.getDistrito());
		}
		return res;
	}

}
