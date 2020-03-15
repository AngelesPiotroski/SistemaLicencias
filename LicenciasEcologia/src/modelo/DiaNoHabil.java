package modelo;

import java.util.Calendar;

public class DiaNoHabil implements Comparable<DiaNoHabil>{
	private Calendar feriado;
        private long IdDiaNoHabil;
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

	//ordenados por dia del a�o(supongo q de menor a mayor)
	public int compareTo(DiaNoHabil dia) {
		int resultado;
		resultado=(this.getFeriado().get(Calendar.DAY_OF_YEAR))-(dia.getFeriado().get(Calendar.DAY_OF_YEAR));
		return resultado;
	}	

        
    public long getIdDiaNoHabil() {
        return IdDiaNoHabil;
    }

    public void setIdDiaNoHabil(long IdDiaNoHabil) {
        this.IdDiaNoHabil = IdDiaNoHabil;
    }
        
}

