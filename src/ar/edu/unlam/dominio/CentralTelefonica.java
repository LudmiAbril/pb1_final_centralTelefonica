package ar.edu.unlam.dominio;

public class CentralTelefonica {
	private static final Integer CANTIDAD_MAXIMA_LLAMADAS = 1000;

	private Integer numero;
	private String nombre;
	private Llamada[] llamadas;

	public CentralTelefonica(Integer numero, String nombre) {
		this.numero = numero;
		this.nombre = nombre;
		this.llamadas = new Llamada[CANTIDAD_MAXIMA_LLAMADAS];
	}

	/**
	 * Ingresa una llamada al array de llamadas
	 * 
	 * @param llamada Llamada que se ingresara
	 * @return verdadero en caso de exito
	 */
	public boolean registrarLlamada(Llamada llamada) {
		boolean repetida=false;
		boolean registrada=false;
		if(buscarPorId(llamada.getId())!=null) {
			repetida=true;
		}
		
		int i=0;
		while(i<llamadas.length && !repetida) {
			if(llamadas[i]==null) {
				llamadas[i]=llamada;
				registrada=true;
				repetida=true;
			}
			i++;
		}
		return registrada;
		
		/* OTRA FORMA MAS CRUDA DE HACERLO 
		 * 
		 * if(buscarPorId(llamada.getId()!=null){
		 * return false;
		 * }
		 *  
		 *  for(int i=0;i<llamadas.length;i++){
		 *    if(llamadas[i]==null){
		 *      llamadas[i]=llamada;
		 *      return true;
		 *      }
		 *  } return false;
		 * 
		 */
	
	}

	/**
	 * Busca el identificador maximo de las llamadas registradas y devuelve este
	 * numero incrementado en uno Si no hay llamadas, debe devolver 1
	 * 
	 * @return Proximo identificador de llamada
	 */
	public Integer obtenerSiguienteIdentificadorDeLlamada() {
		Integer id=0;
		
		for(int i=0;i<llamadas.length;i++) {
			if(llamadas[i]!=null && llamadas[i].getId()>id) {
				id=llamadas[i].getId();
			}
		} return id+1;
	
	}

	/**
	 * Obtiene una llamada por su identificador de llamada
	 * 
	 * @param idLlamada Identificador de la llamada
	 * @return llamada que coincide con el identificador o null en caso de no
	 *         existir
	 */
	public Llamada buscarPorId(Integer idLlamada) {
		for(int i=0;i<llamadas.length;i++) {
			if(llamadas[i]!=null && llamadas[i].getId().equals(idLlamada)) {
				return llamadas[i];
			}
		} return null;
	}

	/**
	 * Obtiene un array de llamadas nacionales realizadas desde un mismo numero de
	 * origen
	 * 
	 * @param numeroOrigen Numero desde donde se realizaron las llamadas
	 * @return array con las llamadas nacionales del numero de origen proporcionado
	 */
	public Llamada[] obtenerLlamadasNacionalesParaNumeroOrigen(String numeroOrigen) {
		Llamada [] llamadasNacionales= new Llamada[this.llamadas.length];
		
		int j=0;
		for(int i=0;i<llamadas.length;i++) {
			if(llamadas[i]!=null && llamadas[i].getTipoLlamada().equals(TipoLlamada.NACIONAL) && llamadas[i].getNumeroTelefonoOrigen().equals(numeroOrigen)) {
				llamadasNacionales[j]=llamadas[i];
				j++;
			}
		}
		ordenarLlamadasPorMinutosDescendente(llamadasNacionales);
		
		return llamadasNacionales;
		
		// TODO: ordenar las llamadas antes de devolverlas utilizando el metodo:
		// ordenarLlamadasPorMinutosDescendente()
	
	}

	/**
	 * Obtiene la llamada mas barata basandose en el tipo de llamada proporcionado
	 * 
	 * @param tipoLlamada Tipo de llamada para obtener la llamada mas barata
	 * @return llamada mas barata o null si no existe una para el tipo de llamada
	 */
	public Llamada obtenerLlamadaMasBarata(TipoLlamada tipoLlamada) {
		double precioref=0.0;
		Llamada barata=null;
		
		for(int r=0;r<llamadas.length;r++) {
			if(llamadas[r]!=null && llamadas[r].getTipoLlamada().equals(tipoLlamada)) {
				precioref=llamadas[r].obtenerCostoLlamada();
				barata=llamadas[r];
			}
		}
		
		
		for(int i=0;i<llamadas.length;i++) {
			if(llamadas[i]!=null && llamadas[i].getTipoLlamada().equals(tipoLlamada) && llamadas[i].obtenerCostoLlamada()<precioref) {
				precioref=llamadas[i].obtenerCostoLlamada();
				barata=llamadas[i];
			}
		}
		return barata;
	}

	/**
	 * Obtiene el promedio de minutos de llamadas internacionales dirigidas a un
	 * mismo numero de destino
	 * 
	 * @param numeroDestino Numero al que se realizaron las llamadas
	 * @return promedio de minutos
	 */
	public Double obtenerPromedioEnMinutosLlamadasInternacionalesParaUnNumeroDestino(String numeroDestino) {
		Double minutos=0.0;
		Integer llamads=0;
		
		for(int i=0;i<llamadas.length;i++) {
			if(llamadas[i]!=null && llamadas[i].getTipoLlamada().equals(TipoLlamada.INTERNACIONAL) 
					&& llamadas[i].getNumeroTelefonoDestino().equals(numeroDestino)) {
				llamads++;
				minutos=+llamadas[i].getDuracion();
			}
		  } 
		if(llamads==0) {
			return 0.0;
		}	
		Double promedio=minutos/llamads;
		return promedio;
	}

	/**
	 * Obtiene la cantidad de llamadas realizadas para un tipo de llamada
	 * 
	 * @param tipoLlamada Tipo de llamada para obtener la cantidad
	 * @return Cantidad de llamadas realizadas o 0.
	 */
	public Integer obtenerCantidadDeLlamadasRealizadasPorTipoLlamada(TipoLlamada tipoLlamada) {
		Integer llamadasTotales=0;
		
		for(int i=0;i<llamadas.length;i++) {
			if(llamadas[i]!=null && llamadas[i].getTipoLlamada().equals(tipoLlamada)) {
				llamadasTotales++;
			}
		} return llamadasTotales;
	}

	/**
	 * Ordena las llamadas proporcionadas por su duracion en minutos de manera
	 * descendente
	 * 
	 * @param llamadas Llamadas que se ordenaran
	 * @return llamadas ordenadas
	 */
	private Llamada[] ordenarLlamadasPorMinutosDescendente(Llamada[] llamadas) {
		Llamada aux;
		for(int i=1;i<llamadas.length;i++) {
			for(int j=0;j<llamadas.length-1;j++){
				if(llamadas[j]!=null && llamadas[j+1]!=null && llamadas[j].getDuracion()<llamadas[j+1].getDuracion()) {
					aux=llamadas[j];
					llamadas[j]=llamadas[j+1];
					llamadas[j+1]=aux;
				}
			}
		}
         return llamadas;
	}
}
