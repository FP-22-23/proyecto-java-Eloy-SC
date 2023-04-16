package fp.crimen.test;

import fp.crimen.Crimenes;
import fp.crimen.FactoriaCrimenes;

public class TestFactoriaCrimenes {

	public static void main(String[] args) {
		testLeerCrimenes("data/crime.csv");
	}

	private static void testLeerCrimenes(String fichero) {
		System.out.println("\nTestLeerCrimenes =============");
		Crimenes crimenes = FactoriaCrimenes.leeCrimenes(fichero);
		System.out.println("Crimenes: "+ crimenes);
	}

}
