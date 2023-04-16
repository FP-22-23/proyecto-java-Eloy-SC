package fp.crimen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CrimenesImpl implements Crimenes {
	
	private List<Crimen> crimenes;
	
	public CrimenesImpl() {
		
		crimenes = new ArrayList<Crimen>();
	}
	
	public CrimenesImpl(Collection<Crimen> crimenes) {
		
		this.crimenes = new ArrayList<Crimen>(crimenes);
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
	
	
}
