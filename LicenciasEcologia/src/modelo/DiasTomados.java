package modelo;

import java.util.Calendar;

public class DiasTomados implements Comparable<DiasCorrespondientesPorAnio>{
	private DiasCorrespondientesPorAnio CorrespAnioTomado;
	private Integer cantDiasTomados;
	private long IdDiasTomados;
        
	public DiasTomados(int cantDiasTomadosDeAnio, DiasCorrespondientesPorAnio d) {
		this.cantDiasTomados=cantDiasTomadosDeAnio;
		this.CorrespAnioTomado=d;
	}


	//getters
	public DiasCorrespondientesPorAnio getCorrespAnioTomado() {
		return CorrespAnioTomado;
	}

        public long getIdDiasTomados() {
            return IdDiasTomados;
        }
	public Integer getCantDiasTomados() {
		return cantDiasTomados;
	}

	//setters
	public void setCorrespAnioTomado(DiasCorrespondientesPorAnio anioTomado) {
		this.CorrespAnioTomado = anioTomado;
	}

        public void setIdDiasTomados(long IdDiasTomados) {
            this.IdDiasTomados = IdDiasTomados;
        }
       
	public void setCantDiasTomados(Integer cantDiasTomados) {
		this.cantDiasTomados = cantDiasTomados;
	}


	//odenado por el anio
	public int compareTo(DiasCorrespondientesPorAnio dias) {
		int resultado;
		resultado=(this.getCorrespAnioTomado().getAnio().get(Calendar.YEAR))-(dias.getAnio().get(Calendar.YEAR));
		return resultado;
	}
}
