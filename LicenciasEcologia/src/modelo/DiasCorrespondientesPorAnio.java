package modelo;

import java.util.Calendar;

public class DiasCorrespondientesPorAnio implements Comparable<DiasCorrespondientesPorAnio>{
	private Integer diasDisponibles;
	private Integer diasOcupados;
	private Calendar anio;
	private Calendar fechaVto;
	private boolean enVigencia;//si se encuentra vencida o no(si toma vigencia la fecha de vto o no)
	
	public DiasCorrespondientesPorAnio(Integer diasDisponibles, Integer diasOcupados, Calendar anio) {
		this.diasDisponibles=diasDisponibles;
		this.diasOcupados=diasOcupados;
		this.anio=anio;
	}

	//toString
	public String toString() {
		return new String("Año: "+this.getAnio().get(Calendar.YEAR)+" Remanente: "+this.getDiasDisponibles()+" Tomados: "+this.getDiasOcupados());
	}
	
	//getters
	public Integer getDiasDisponibles() {
		return diasDisponibles;
	}
	
	public Integer getDiasOcupados() {
		return diasOcupados;
	}
	
	public Calendar getAnio() {
		return anio;
	}
	
	public boolean isEnVigencia() {
		return enVigencia;
	}
	
	public Calendar getFechaVto() {
		return fechaVto;
	}
	
	//setters
	public void setDiasDisponibles(Integer diasDisponibles) {
		this.diasDisponibles = diasDisponibles;
	}
	
	public void setDiasOcupados(Integer diasOcupados) {
		this.diasOcupados = diasOcupados;
	}
	
	public void setAnio(Calendar anio) {
		this.anio = anio;
	}

	public void setEnVigencia(boolean enVigencia) {
		this.enVigencia = enVigencia;
	}
	
	public void setFechaVto(Calendar fechaVto) {
		this.fechaVto = fechaVto;
	}
	
	//ordena los dias correspo desde el mas viejo 
	public int compareTo(DiasCorrespondientesPorAnio dias) {
		int resultado;
		resultado=(this.getAnio().get(Calendar.YEAR))-(dias.getAnio().get(Calendar.YEAR));
		return resultado;
	}
}
