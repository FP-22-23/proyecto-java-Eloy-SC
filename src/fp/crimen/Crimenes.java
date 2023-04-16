package fp.crimen;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
}
