package modelo;

import java.util.ArrayList;
import java.util.Calendar;

public class Licencia implements Comparable{
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private ArrayList<DiasTomados> correspondientesTomados  = new ArrayList<>();

    public Licencia(Calendar fechaInicio, Calendar fechaFin, ArrayList<DiasTomados> correspondientesTomados) {
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.correspondientesTomados=correspondientesTomados;
    }
	
	//getters

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

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
        
        public int compareTo(Licencia licComparar){
            int result;
            result=this.fechaInicio.compareTo(licComparar.getFechaInicio());
            return result;
        }

        @Override
        public int compareTo(Object t) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
}
