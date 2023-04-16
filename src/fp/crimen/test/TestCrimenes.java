package fp.crimen.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fp.crimen.Crimen;
import fp.crimen.Crimenes;
import fp.crimen.DiaSemana;
import fp.crimen.FactoriaCrimenes;
import fp.crimen.Resolucion;

public class TestCrimenes {

	public static void main(String[] args) {
		
		Crimenes crimenes = FactoriaCrimenes.leeCrimenes("data/crime.csv");
		List<String> desc1 = new ArrayList<>();
		Crimen c1 = new Crimen(LocalDateTime.of(2020, 11, 12, 17, 40), "ASSAULT", desc1, DiaSemana.JUEVES, 
				"TENDERLOIN", Resolucion.ARRESTADO, "Block of WILLIAMS AV", -122.3977444, 37.72993469);
		crimenes.agregarCrimen(c1);
		crimenes.eliminarCrimen(0);
		List<Crimen> lista = new ArrayList<>();
		lista.add(c1);
		crimenes.agregarCrimenes(lista);
		
		System.out.println(crimenes.getNumeroCrimenes());
		System.out.println(crimenes.existeCategoria("ASSAULT"));
		System.out.println(crimenes.sumaDistritos());
		System.out.println(crimenes.listaDirecciones(DiaSemana.MIERCOLES));
		System.out.println(crimenes.contarDistritos());
		System.out.println(crimenes.direccionesPorDistrito());
	}

}
