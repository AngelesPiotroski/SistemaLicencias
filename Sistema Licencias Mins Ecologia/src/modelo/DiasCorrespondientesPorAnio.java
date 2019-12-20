package modelo;

import java.util.Calendar;

public class DiasCorrespondientesPorAnio {
	private Integer diasDisponibles;
	private Integer diasOcupados;
	private Calendar anio;
	
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

}
