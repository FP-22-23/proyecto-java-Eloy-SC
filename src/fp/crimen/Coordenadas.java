package fp.crimen;

import fp.utiles.Checkers;

public record Coordenadas(Double latitud, Double longitud) {
	
	public Coordenadas {
		Checkers.checkNoNull(latitud, longitud);
	}
}
