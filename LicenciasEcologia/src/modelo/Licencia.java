package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Licencia {
	private Integer cantDiasTomados;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private ArrayList<DiasTomados> correspondientesTomados  = new ArrayList<>();
	
	//getters
	public Integer getCantDiasTomados() {
		return cantDiasTomados;
	}
	
	public ArrayList<DiasTomados> getCorrespondientesTomados() {
		return correspondientesTomados;
	}


	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	
	public Calendar getFechaFin() {
		return fechaFin;
	}
	
	//setters
	
	public void setCorrespondientesTomados(ArrayList<DiasTomados> correspondientesTomados) {
		this.correspondientesTomados = correspondientesTomados;
	}
	
	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setCantDiasTomados(Integer cantDiasTomados) {
		this.cantDiasTomados = cantDiasTomados;
	}
	
	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
}
