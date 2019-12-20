package modelo;

import java.util.Calendar;

public class Licencia {
	private Integer cantDiasTomados;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	
	//getters
	public Integer getCantDiasTomados() {
		return cantDiasTomados;
	}
	
	public Calendar getFechaInicio() {
		return fechaInicio;
	}
	
	public Calendar getFechaFin() {
		return fechaFin;
	}
	
	//setters
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
