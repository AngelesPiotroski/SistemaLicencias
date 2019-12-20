package modelo;

import java.util.Calendar;

public class DiaNoHabil {
	private Calendar feriado;

	//contrusctor
	public DiaNoHabil(Calendar fechaAgregar) {
		this.feriado=fechaAgregar;
	}
	
	//getter
	public Calendar getFeriado() {
		return feriado;
	}
	
	//setter
	public void setFeriado(Calendar feriado) {
		this.feriado = feriado;
	}	
}
