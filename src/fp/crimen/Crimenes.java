package fp.crimen;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public interface Crimenes {
	
	Integer getNumeroCrimenes();
	
	void agregarCrimen(Crimen c);
	
	void agregarCrimenes(Collection<Crimen> crimenes);
	
	void eliminarCrimen(Integer index);
	
	Boolean existeCategoria(String cat);
	
	Integer sumaDistritos();
	
	List<String> listaDirecciones(DiaSemana diaSemana);
	
	Map<String, List<String>> direccionesPorDistrito();
	
	Map<String, Integer> contarDistritos();
	
	Boolean existeCategoriaStream(String cat);
	
	Integer sumaDistritosStream();
	
	List<String> listaDireccionesStream(DiaSemana diaSemana);
	
	Crimen crimenMayorPrioridadEnDistrito(String distrito);
	
	List<Crimen> crimenesPrioridadMayorOrdenadosPorDistrito(Integer prioridad);
	
	Map<String, Long> contarDistritosStream();
	
	Map<String, Integer> contarCrimenesSinResolverPorCategoria();
	
	Map<Resolucion, Crimen> crimenesDeMayorPrioridadPorResoluciones();
	
	SortedMap<DiaSemana, List<Crimen>> nCrimenesOrdenadosPorDiaDeSemana(Integer n);
	
	String categoriaConMayorPrioridad();
}
