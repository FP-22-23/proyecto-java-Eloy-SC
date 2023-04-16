package fp.crimen;

import java.util.Collection;

public interface Crimenes {
	
	Integer getNumeroCrimenes();
	
	void agregarCrimen(Crimen c);
	
	void agregarCrimenes(Collection<Crimen> crimenes);
	
	void eliminarCrimen(Integer index);
}
