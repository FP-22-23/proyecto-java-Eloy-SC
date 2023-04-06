package fp.crimen.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import fp.crimen.Crimen;
import fp.crimen.DiaSemana;
import fp.crimen.Resolucion;

public class TestCrimen {

	public static void main(String[] args) {
		
		Crimen c1 = new Crimen(LocalDateTime.of(2020, 11, 12, 17, 40), "ROBBERY", Resolucion.SIN_RESOLUCION);
		System.out.println(c1);
		System.out.println(c1.getResuelto());
		System.out.println(c1.getPrioridad());
		List<String> descripcion = new ArrayList<>();
		Crimen c2 = new Crimen(LocalDateTime.of(2020, 11, 12, 17, 40), "ASSAULT", descripcion, DiaSemana.JUEVES, 
				"TENDERLOIN", Resolucion.ARRESTADO, "Block of WILLIAMS AV", -122.3977444, 37.72993469);
		System.out.println(c2);
		System.out.println(c2.getFecha());
		System.out.println(c2.getCategoria());
		System.out.println(c2.getDesc());
		System.out.println(c2.getDiaSem());
		System.out.println(c2.getDistrito());
		System.out.println(c2.getResolucion());
		System.out.println(c2.getDireccion());
		System.out.println(c2.getLat());
		System.out.println(c2.getLon());
		System.out.println(c2.getResuelto());
		System.out.println(c2.getPrioridad());
	}

}
