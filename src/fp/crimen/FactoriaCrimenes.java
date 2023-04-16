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
		
		LocalDateTime fecha = LocalDateTime.parse(trozos[0].trim(), DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm"));
		String categoria = trozos[1].trim();
		List<String> descripcion = List.of(trozos[2].trim().split(","));
		DiaSemana diaSemana = DiaSemana.valueOf(trozos[3].trim().toUpperCase());
		String distrito = trozos[4].trim();
		Resolucion resolucion = Resolucion.valueOf(trozos[5].trim().toUpperCase());
		String direccion = trozos[6].trim();
		Double latitud = Double.parseDouble(trozos[7].trim());
		Double longitud = Double.parseDouble(trozos[8].trim());
		
		return new Crimen(fecha, categoria, descripcion, diaSemana, distrito, resolucion, direccion, latitud, longitud);
	}
}
