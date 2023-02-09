package ar.edu.unlam.dominio;

public class Llamada {
	private static final Double COSTO_LLAMADA_NACIONAL = 1.1, COSTO_LLAMADA_INTERNACIONAL = 9.3;
	
	private Integer id;
	private String numeroTelefonoOrigen;
	private String numeroTelefonoDestino;
	private Double duracion; // Minutos
	private TipoLlamada tipoLlamada;

	public Llamada(Integer id, String numeroTelefonoOrigen, String numeroTelefonoDestino, Double duracion,
			TipoLlamada tipoLlamada) {
		this.id = id;
		this.numeroTelefonoOrigen = numeroTelefonoOrigen;
		this.numeroTelefonoDestino = numeroTelefonoDestino;
		this.duracion = duracion;
		this.tipoLlamada = tipoLlamada;
	}
	
	/**
	 * Calcula y devuelve el costo de una llamada basandose en los minutos de duracion y el tipo de llamada (Nacional o Internacional)
	 * @return costo de la llamada
	 * */
	public Double obtenerCostoLlamada() {
		Double costo=0.0;
		
	if(this.tipoLlamada.equals(TipoLlamada.INTERNACIONAL)) {
		costo=this.duracion*COSTO_LLAMADA_INTERNACIONAL;
	}else {
		costo=this.duracion*COSTO_LLAMADA_NACIONAL;
	}
	
	return costo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroTelefonoOrigen() {
		return numeroTelefonoOrigen;
	}

	public void setNumeroTelefonoOrigen(String numeroTelefonoOrigen) {
		this.numeroTelefonoOrigen = numeroTelefonoOrigen;
	}

	public String getNumeroTelefonoDestino() {
		return numeroTelefonoDestino;
	}

	public void setNumeroTelefonoDestino(String numeroTelefonoDestino) {
		this.numeroTelefonoDestino = numeroTelefonoDestino;
	}

	public Double getDuracion() {
		return duracion;
	}

	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}

	public TipoLlamada getTipoLlamada() {
		return tipoLlamada;
	}

	public void setTipoLlamada(TipoLlamada tipoLlamada) {
		this.tipoLlamada = tipoLlamada;
	}

	@Override
	public String toString() {
		return "Llamada [id=" + id + ", numeroTelefonoOrigen=" + numeroTelefonoOrigen + ", numeroTelefonoDestino="
				+ numeroTelefonoDestino + ", duracion=" + duracion + ", tipoLlamada=" + tipoLlamada + ", costo=" + this.obtenerCostoLlamada() + "]";
	}
	
	
}
