package ar.edu.unlam.interfaz;

import java.util.Scanner;

import ar.edu.unlam.dominio.CentralTelefonica;
import ar.edu.unlam.dominio.Llamada;
import ar.edu.unlam.dominio.TipoLlamada;

public class GestionDeLlamadas {

	private static final int REGISTRAR_LLAMADA = 1, MOSTRAR_LLAMADA_POR_ID = 2,
			MOSTRAR_LLAMADAS_NACIONALES_MISMO_NUMERO_ORIGEN = 3, MOSTRAR_LLAMADA_MAS_BARATA = 4,
			MOSTRAR_PROMEDIO_DE_MINUTOS_LLAMADAS_INTERNACIONALES_MISMO_NUMERO_DESTINO = 5,
			MOSTRAR_CANTIDAD_LLAMADAS_REALIZADAS = 6, SALIR = 9;
	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {
		mostrarMensaje("Bienvenido al gestor de llamadas");

		CentralTelefonica centralTelefonica = new CentralTelefonica(1903, "Central telefonica UNLaM");
		int opcion = 0;

		do {
			mostrarMenu();
			opcion = teclado.nextInt();
			Llamada llamada = null;

			switch (opcion) {
			case REGISTRAR_LLAMADA:
				// TODO: ingresar una llamada por teclado utilizando el metodo
				// ingresarLlamadaPorTeclado() de esta clase
				llamada=ingresarLlamadaPorTeclado(centralTelefonica);
				// TODO: registrar la llamada en la central consumiendo el metodo
				// registrarLlamada de la central telefonica
				boolean registrada=centralTelefonica.registrarLlamada(llamada);
				// TODO: mostrar un mensaje de exito en caso de poder registrar la llamada o un
				// mensaje de error, en caso de no lograrlo.
				if(registrada) {
					System.out.println("se registro la llamda");
				}else {
					System.out.println(" no se pudo registrar la llamada");
				}
				break;
			case MOSTRAR_LLAMADA_POR_ID:
				// TODO: ingresar por teclado el ID de la llamada
				System.out.println("ingrese el id de la llamada");
				Integer id=teclado.nextInt();

				// TODO: obtener una llamada consumiendo el metodo buscarPorId() de la central
				// telefonica
				llamada=centralTelefonica.buscarPorId(id);

				// TODO: mostrar la llamada con el metodo toString() de la misma o un mensaje de
				// error en caso de ser null.
				if(llamada!=null) {
					System.out.println(llamada.toString());
				}else {
					System.out.println("no se encontro la llamada");
				}
				
				break;
			case MOSTRAR_LLAMADAS_NACIONALES_MISMO_NUMERO_ORIGEN:
				// TODO: ingresar por teclado el numero de origen
				System.out.println("ingrese el numero de origen");
				String numOrigen=teclado.next();
				// TODO: obtener las llamadas consumiendo el metodo
				// obtenerLlamadasNacionalesParaNumeroOrigen() de la central telefonica
				Llamada [] llamadas=centralTelefonica.obtenerLlamadasNacionalesParaNumeroOrigen(numOrigen);

				// TODO: mostrar las llamadas obtenidas con el metodo mostrarLlamadas() de esta
				// clase, solo si no son nulas
				if(llamadas!=null) {
					mostrarLlamadas(llamadas);
				}else {
					System.out.println("no hay llamadas registradas aun");
				}
				
				break;
			case MOSTRAR_LLAMADA_MAS_BARATA:
				// TODO: ingresar por teclado el tipo de llamada
				TipoLlamada tipo=ingresarTipoLlamada();
				// TODO: obtener la llamada mas barata consumiendo el metodo
				// obtenerLlamadaMasBarata de la central telefonica
				llamada=centralTelefonica.obtenerLlamadaMasBarata(tipo);

				// TODO: mostrar un mensaje mostrando la llamada mas barata o un mensaje de
				// error en caso de ser null
				if(llamada!=null) {
					System.out.println(llamada.toString());
				} else {
					System.out.println("no se encontro la llamada");
				}
				
				break;
			case MOSTRAR_PROMEDIO_DE_MINUTOS_LLAMADAS_INTERNACIONALES_MISMO_NUMERO_DESTINO:
				// TODO: Ingresar por teclado el numero de destino
				String numDestino=ingresarString("ingrese el num destino");
				// TODO: obtener el promedio consumiendo el metodo
				// obtenerPromedioEnMinutosLlamadasInternacionalesParaUnNumeroDestino de la
				// central telefonica
				Double prom=centralTelefonica.obtenerPromedioEnMinutosLlamadasInternacionalesParaUnNumeroDestino(numDestino);
				// TODO: mostrar el promedio por pantalla
				System.out.println("el promedio de minutos para las llamadas internacionales del numero " + numDestino + " es:" + prom);
				break;
			case MOSTRAR_CANTIDAD_LLAMADAS_REALIZADAS:
				// TODO: obtener la cantidad de llamadas nacionales e internacionales realizadas
				// consumiendo el metodo obtenerCantidadDeLlamadasRealizadasPorTipoLlamada() de
				// la central telefonica
				Integer cantNac=centralTelefonica.obtenerCantidadDeLlamadasRealizadasPorTipoLlamada(TipoLlamada.NACIONAL);
				Integer cantInternac=centralTelefonica.obtenerCantidadDeLlamadasRealizadasPorTipoLlamada(TipoLlamada.INTERNACIONAL);
				// TODO: mostrar por pantalla ambas cantidades en un mismo mensaje.

				System.out.println("cantidad de llamadas nacionales: " + cantNac
						+ "\n cantidad de llamadas internacionales: " + cantInternac);
				
				break;
			case SALIR:
				mostrarMensaje("Hasta luego!");
				break;
			}

		} while (opcion != SALIR);
	}

	/**
	 * Ingresa una llamada por teclado
	 * 
	 * @param centralTelefonica Central telefonica para obtener el siguiente
	 *                          identificador de llamada
	 * @return Llamada ingresada
	 */
	private static Llamada ingresarLlamadaPorTeclado(CentralTelefonica centralTelefonica) {
		// TODO: obtener el proximo identificador de la llamada consumiendo el metodo
		// obtenerSiguienteIdentificadorDeLlamada() de la central 
        Integer id=centralTelefonica.obtenerSiguienteIdentificadorDeLlamada();
        String numOrigen=ingresarString("ingrese el numero de origen");
        String numDestino=ingresarString("ingrese el numero de destino");
        System.out.println("ingrese la duracion de la llamda");
        Double duracion=teclado.nextDouble();
        TipoLlamada tipo=ingresarTipoLlamada();
        
        Llamada llamada=new Llamada(id,numOrigen,numDestino,duracion,tipo);
        return llamada;
	}

	private static String ingresarString(String mensaje) {
		mostrarMensaje(mensaje);
		return teclado.next();
	}

	private static TipoLlamada ingresarTipoLlamada() {
		int opcion;
		do{
		System.out.println("ingrese el tipo de llamada"
				+ "\n 1 - NACIONAL"
				+ "\n 2 - INTERNACIONAL");
		opcion=teclado.nextInt();
		}while(opcion<1 || opcion>2);
		
		TipoLlamada tipo=TipoLlamada.values()[opcion-1];
		
        return tipo;
		
	}

	private static void mostrarLlamadas(Llamada[] llamadas) {
	for(int i=0;i<llamadas.length;i++) {
		if(llamadas[i]!=null) {
			System.out.println(llamadas[i].toString());
		}
	  }
	}

	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	private static void mostrarMenu() {
		mostrarMensaje("\nMenu principal");
		mostrarMensaje("1 - Registrar llamada");
		mostrarMensaje("2 - Mostrar llamada por su identificador");
		mostrarMensaje("3 - Mostrar llamadas nacionales para un mismo numero de origen");
		mostrarMensaje("4 - Mostrar llamada mas barata para un tipo de llamada");
		mostrarMensaje(
				"5 - Mostrar promedio de minutos para llamadas internacionales realizadas a un mismo numero de destino");
		mostrarMensaje("6 - Mostrar cantidad de llamadas nacionales e internacionales realizadas");
		mostrarMensaje("9 - Salir");
	}

}
