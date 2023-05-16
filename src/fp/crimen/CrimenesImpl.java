package fp.crimen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CrimenesImpl implements Crimenes {
	
	private List<Crimen> crimenes;
	
	public CrimenesImpl() {
		
		crimenes = new ArrayList<Crimen>();
	}
	
	public CrimenesImpl(Collection<Crimen> crimenes) {
		
		this.crimenes = new ArrayList<Crimen>(crimenes);
	}
	
	public CrimenesImpl(Stream<Crimen> crimenes) {
		this.crimenes = crimenes.collect(Collectors.toList());
	}

	@Override
	public int hashCode() {
		return Objects.hash(crimenes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrimenesImpl other = (CrimenesImpl) obj;
		return Objects.equals(crimenes, other.crimenes);
	}
	
	@Override
	public String toString() {
		String crimenesString = crimenes.stream().map(Object::toString).collect(Collectors.joining("\n"));
		return "Partidas [numCrimenes =" + getNumeroCrimenes() + " crimenes=" + crimenesString + "]";
	}
	
	public Integer getNumeroCrimenes() {
		return crimenes.size();
	}
	
	public void agregarCrimen(Crimen c) {
		crimenes.add(c);
	}
	
	public void agregarCrimenes(Collection<Crimen> crimenes) {
		this.crimenes.addAll(crimenes);
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public void eliminarCrimen(Integer index) {
		crimenes.remove(index);
	}
	
	public Boolean existeCategoria(String cat) {
		
		Boolean res = false;
		for(Crimen c:crimenes) {
			if (c.getCategoria()==cat) {
				res = true;
				break;
			}
		}
		return res;
	}
	
	public Integer sumaDistritos() {
		
		Set<String> res = new HashSet<>();
		for(Crimen c:crimenes) {
			res.add(c.getDistrito());
		}
		return res.size();
	}
	
	public List<String> listaDirecciones(DiaSemana diaSemana) {
		
		List<String> res = new ArrayList<>();
		for(Crimen c:crimenes) {
			if (c.getDiaSem()==diaSemana) {
				res.add(c.getDireccion());
			}
		}
		return res;
	}
	
	public Map<String, List<String>> direccionesPorDistrito() {
		
		Map<String, List<String>> res = new HashMap<String, List<String>>();
		for(Crimen c:crimenes) {
			if (res.containsKey(c.getDistrito())) {
				res.get(c.getDistrito()).add(c.getDireccion());
			}
			else {
				ArrayList<String> direcciones = new ArrayList<>();
				direcciones.add(c.getDireccion());
				res.put(c.getDistrito(), direcciones);
			}
		}
		return res;
	}
	
	public Map<String, Integer> contarDistritos() {
		Map<String, Integer> m = new HashMap<String, Integer>();
		for (Crimen c : crimenes) {
			String clave = c.getDistrito();
			if (m.containsKey(clave)) {
				m.put(clave, m.get(clave) + 1);
			} else {
				m.put(clave, 1);
			}
		}
		return m;
	}
	
	public Boolean existeCategoriaStream(String cat) {
		
		Boolean res = false;
		Long contador = crimenes.stream()
						   .filter(x->x.getCategoria().equals(cat))
						   .count();
		if(contador>0) {
			res = true;
		}
		return res;
	}
	
	public Integer sumaDistritosStream() {
		
		Long res = crimenes.stream()
					   .map(x -> x.getDistrito())
					   .distinct()
					   .count();
		return res.intValue();
	}
	
	public List<String> listaDireccionesStream(DiaSemana diaSemana) {
		
		return crimenes.stream()
					   .filter(x -> x.getDiaSem().equals(diaSemana))
					   .map(x -> x.getDireccion())
					   .toList();
	}
	
	public String direccionMayorPrioridadEnDistrito(String distrito) {
		
		return crimenes.stream()
					  .filter(x -> x.getDistrito().equals(distrito))
					  .max(Comparator.comparing(Crimen::getPrioridad))
					  .map(Crimen::getDireccion)
					  .orElse(null);
	}
}
