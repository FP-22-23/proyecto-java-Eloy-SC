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
		System.out.println(crimenes.existeCategoriaStream("ROBBERY"));
		System.out.println(crimenes.sumaDistritosStream());
		System.out.println(crimenes.listaDireccionesStream(DiaSemana.MIERCOLES));
		System.out.println(crimenes.crimenMayorPrioridadEnDistrito("INGLESIDE"));
		System.out.println(crimenes.crimenesPrioridadMayorOrdenadosPorDistrito(50));
		System.out.println(crimenes.contarDistritosStream());
		System.out.println(crimenes.contarCrimenesSinResolverPorCategoria());
		System.out.println(crimenes.crimenesDeMayorPrioridadPorResoluciones());
		System.out.println(crimenes.nCrimenesOrdenadosPorDiaDeSemana(3));
		System.out.println(crimenes.categoriaMasRepetida());
	}

}
