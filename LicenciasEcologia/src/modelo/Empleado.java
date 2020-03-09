package modelo;

import java.util.ArrayList;
import java.util.Calendar;

import exceptions.ErrorEmpleado;

public class Empleado {
	private Integer nroLegajo;
	private String nombre, apellido;
	private Calendar antiguedadEmpleado;
	private ArrayList<DiasCorrespondientesPorAnio> correspondiestes=new ArrayList<DiasCorrespondientesPorAnio>();
	private ArrayList<Licencia> licenciasTomadas=new ArrayList<Licencia>();
	
	//constructores
	public Empleado(Integer nroLegajo) {
		this.nroLegajo=nroLegajo;
	}
	
	public Empleado(Integer nroLegajo, String nombre, String apellido, Calendar antiguedadEmpleado) {
		super();
		this.nroLegajo = nroLegajo;
		this.nombre = nombre;
		this.apellido = apellido;
		this.antiguedadEmpleado = antiguedadEmpleado;
	}

	//toString
	public String toString() {
		return new String(this.apellido+", "+this.nombre+" Legajo: "+Integer.toString(this.getNroLegajo()));
	}
	
	//getters
	public Integer getNroLegajo() {
		return nroLegajo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public Calendar getAntiguedadEmpleado() {
		return antiguedadEmpleado;
	}
	public ArrayList<DiasCorrespondientesPorAnio> getCorrespondiestes() {
		return correspondiestes;
	}
	public ArrayList<Licencia> getLicenciasTomadas() {
		return licenciasTomadas;
	}
	
	//setters
	public void setNroLegajo(Integer nroLegajo) {
		this.nroLegajo = nroLegajo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public void setAntiguedadEmpleado(Calendar antiguedadEmpleado) {
		this.antiguedadEmpleado = antiguedadEmpleado;
	}
	public void setCorrespondiestes(ArrayList<DiasCorrespondientesPorAnio> correspondiestes) {
		this.correspondiestes = correspondiestes;
	}
	public void setLicenciasTomadas(ArrayList<Licencia> licenciasTomadas) {
		this.licenciasTomadas = licenciasTomadas;
	}

////////////////////////////// DIAS CORRESPONDIENTES	//////////////////////////////	
	//buscar dias correspondientes por anio
	public DiasCorrespondientesPorAnio buscarDiasCorrespondientesPorAnio(Calendar anioBuscar) {
		DiasCorrespondientesPorAnio diasBuscar =null;
		for(DiasCorrespondientesPorAnio d : this.correspondiestes)
		{
			if(d.getAnio().equals(anioBuscar)) {
				diasBuscar=d;
				break;
			}
		}
		return diasBuscar;
	}
	
	//agregar dias correspondientes por anio
	public void agregarDiasCorrespondientesPorAnio(Integer diasDisponibles, Integer diasOcupados, Calendar anio) throws ErrorEmpleado {
		DiasCorrespondientesPorAnio diasBuscar = buscarDiasCorrespondientesPorAnio(anio);
		if(diasBuscar==null)
		{
			DiasCorrespondientesPorAnio diasAgregar = new DiasCorrespondientesPorAnio(diasDisponibles,diasOcupados,anio);
			this.correspondiestes.add(diasAgregar);
		}else {
			throw new ErrorEmpleado("Ya existe dias correspondientes al año ("+diasBuscar.toString()+") al Empleado ("+this.toString()+").");
		}
	}
	
	//borrar dias correspondeintes por anio
	public void borrarDiasCorrespondientesPorAnio(Calendar anioBorrar) throws ErrorEmpleado {
		DiasCorrespondientesPorAnio diasBorrar =buscarDiasCorrespondientesPorAnio(anioBorrar);
		if(diasBorrar!=null)
		{
			this.correspondiestes.remove(diasBorrar);
		}else {
			throw new ErrorEmpleado("No se encontraron dias correspondientes al año ("+anioBorrar+").");
		}
	}
}
