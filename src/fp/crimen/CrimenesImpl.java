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
import java.util.function.Predicate;
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
		return "Partidas [numPartidas =" + getNumeroCrimenes() + " partidas=" + crimenesString + "]";
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
}
