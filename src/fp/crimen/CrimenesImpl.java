package fp.crimen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.function.BinaryOperator;
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
	
	
	
//	ENTREGA 3
	
//	BLOQUE I:
	
	public Boolean existeCategoriaStream(String cat) {
		
		return crimenes.stream()
					   .anyMatch(x->x.getCategoria().equals(cat));
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
	
	public Crimen crimenMayorPrioridadEnDistrito(String distrito) {
		
		return crimenes.stream()
					  .filter(x -> x.getDistrito().equals(distrito))
					  .max(Comparator.comparing(Crimen::getPrioridad))
					  .orElse(null);
	}
	
	public List<Crimen> crimenesPrioridadMayorOrdenadosPorDistrito(Integer prioridad) {
		
		return crimenes.stream()
					   .filter(x -> x.getPrioridad()>prioridad)
					   .sorted(Comparator.comparing(Crimen::getDistrito))
					   .toList();
	}
	
//	BLOQUE II:
	
	public Map<String, Long> contarDistritosStream() {
		
		return crimenes.stream()
					   .collect(Collectors.groupingBy(Crimen::getDistrito, Collectors.counting()));
	}
	
	public Map<String, Integer> contarCrimenesSinResolverPorCategoria() {
		
		return crimenes.stream()
					   .filter(x->x.getResuelto()==false)
					   .collect(Collectors.groupingBy(Crimen::getCategoria, 
							   Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
	}
	
	public Map<Resolucion, Crimen> crimenesDeMayorPrioridadPorResoluciones() {
		
		return crimenes.stream()
					   .collect(Collectors.toMap(
							   			  Crimen::getResolucion,
							   			  x->x, 
							   			  BinaryOperator.maxBy(Comparator.comparing(Crimen::getPrioridad))));
	}
	
	public SortedMap<DiaSemana, List<Crimen>> nCrimenesOrdenadosPorDiaDeSemana(Integer n) {
		
		Map<DiaSemana, List<Crimen>> maux = crimenes.stream()
											 	   	.collect(Collectors.groupingBy(
													 Crimen::getDiaSem,
													 Collectors.collectingAndThen(
															 Collectors.toList(),
													 		 lista->nCrimenesOrdenados(lista,n))
													 ));
		SortedMap<DiaSemana, List<Crimen>> res = new TreeMap<>();
		res.putAll(maux);
		return res;
	}
	
	private List<Crimen> nCrimenesOrdenados(List<Crimen> listaCrimenes, Integer n) {
		return listaCrimenes.stream()
						    .sorted()
						    .limit(n)
						    .collect(Collectors.toList());
	}
	
	public String categoriaMasRepetida() {
		
		Map<String, Long> m = crimenes.stream()
					   					 .collect(Collectors.groupingBy(Crimen::getCategoria, Collectors.counting()));
		
		return Collections.max(m.entrySet(), Map.Entry.comparingByValue()).toString();
	}
}
