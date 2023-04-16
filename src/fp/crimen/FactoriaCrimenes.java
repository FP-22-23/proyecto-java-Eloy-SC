package fp.crimen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FactoriaCrimenes {
	
	public static Crimenes leeCrimenes(String fichero) {
		
		Crimenes res = null;
		try {
			List<Crimen> crimenes = Files.lines(Paths.get(fichero))
										.skip(1)
										.map(FactoriaCrimenes::parsearCrimen)
										.collect(Collectors.toList());
			res = new CrimenesImpl(crimenes);
		} catch(IOException e) {
			System.out.println("Fichero no encontrado:" + e);
			e.printStackTrace();
		}
		return res;
	}
	
	private static Crimen parsearCrimen(String linea) {
		String[] trozos = linea.split(";");
		
		LocalDateTime fecha = LocalDateTime.parse(trozos[0].trim(), DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
		String categoria = trozos[1].trim();
		List<String> descripcion = List.of(trozos[2].trim().split(","));
		DiaSemana diaSemana = parseaDiaSemana(trozos[3].trim());
		String distrito = trozos[4].trim();
		Resolucion resolucion = parseaResolucion(trozos[5].trim());
		String direccion = trozos[6].trim();
		Double latitud = Double.parseDouble(trozos[7].trim());
		Double longitud = Double.parseDouble(trozos[8].trim());
		
		return new Crimen(fecha, categoria, descripcion, diaSemana, distrito, resolucion, direccion, latitud, longitud);
	}
	
	public static DiaSemana parseaDiaSemana(String cad) {
		DiaSemana res = null;
		cad = cad.toUpperCase();
		if (cad.equals("Monday")) {
			res = DiaSemana.LUNES;
		}
		else if (cad.equals("Tuesday")) {
			res = DiaSemana.MARTES;
		}
		else if (cad.equals("Wednesday")) {
			res = DiaSemana.MIERCOLES;
		}
		else if (cad.equals("Thursday")) {
			res = DiaSemana.JUEVES;
		}
		else if (cad.equals("Friday")) {
			res = DiaSemana.VIERNES;
		}
		else if (cad.equals("Saturday")) {
			res = DiaSemana.SABADO;
		}
		else if (cad.equals("Sunday")) {
			res = DiaSemana.DOMINGO;
		}
		return res;
	}
	
	public static Resolucion parseaResolucion(String cad) {
		Resolucion res = null;
		cad = cad.toUpperCase();
		if (cad.equals("ARREST, BOOKED")) {
			res = Resolucion.ARRESTADO;
		}
		else if (cad.equals("ARREST, CITED")) {
			res = Resolucion.ARRESTADO_CITADO;
		}
		else {
			res = Resolucion.SIN_RESOLUCION;
		}
		return res;
	}
}
