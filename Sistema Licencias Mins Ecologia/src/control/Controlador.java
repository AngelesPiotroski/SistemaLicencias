package control;

import java.util.ArrayList;
import java.util.Calendar;

import exceptions.ErrorControlador;
import modelo.*;

public class Controlador {
	private ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	private ArrayList<DiaNoHabil> feriados= new ArrayList<DiaNoHabil>();
	private Calendar fechaActual;
	
	//getters
	public ArrayList<Empleado> getEmpleados() {
		return empleados;
	}
	public ArrayList<DiaNoHabil> getFeriados() {
		return feriados;
	}
	public Calendar getFechaActual() {
		return fechaActual;
	}
	
	//setters
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.empleados = empleados;
	}
	public void setFeriados(ArrayList<DiaNoHabil> feriados) {
		this.feriados = feriados;
	}
	public void setFechaActual(Calendar fechaActual) {
		this.fechaActual = fechaActual;
	}
	public void addEmpleado(Empleado empleAgregar) {
		this.empleados.add(empleAgregar);
	}
	public void addDiaNoHabil(DiaNoHabil diaNoHabilAgregar) {
		this.feriados.add(diaNoHabilAgregar);
	}
////////////////////////////// DIA NO HABIL	//////////////////////////////
	//buscar diaNoHabil
	public DiaNoHabil buscarDiaNoHabil(Calendar fechaBuscar) {
		DiaNoHabil diaNoHabilBuscar=null;
		for(DiaNoHabil d : this.feriados) {
			if(d.getFeriado().equals(fechaBuscar)) //si se encunetra la misma fecha
			{
				diaNoHabilBuscar=d;
				break;
			}
		}
		return diaNoHabilBuscar;
	}
	
	//agregar un dia no habil
	public void agregarDiaNoHabil(Calendar fechaAgregar) throws ErrorControlador {
		DiaNoHabil diaNoHabilBuscar=buscarDiaNoHabil(fechaAgregar);
		if(diaNoHabilBuscar==null)//si no se encuentra
		{
			DiaNoHabil diaNoHabilAgregar = new DiaNoHabil(fechaAgregar);
			this.addDiaNoHabil(diaNoHabilAgregar);
		}else {
			throw new ErrorControlador("Ya existe un dia no habil con la fecha ("+diaNoHabilBuscar.getFeriado().getTime()+").");
		}
	}
	//borrar un dia no habil
	public void borrarDiaNoHabil(Calendar fechaBorrar) throws ErrorControlador {
		DiaNoHabil diaNoHabilBuscar = buscarDiaNoHabil(fechaBorrar);
		if(diaNoHabilBuscar!=null)//si lo encontro
		{
			this.feriados.remove(diaNoHabilBuscar);
		}else {
			throw new ErrorControlador("No se encontro el dia no habil a eliminar ("+fechaBorrar.getTime()+").");
		}
	}
//////////////////////////////	EMPLEADO	//////////////////////////////	
	//buscar un empleado
	public Empleado buscarEmpleado(Integer nroLegajoBuscar){
		Empleado empleadoBuscado=null;
		for(Empleado e : this.empleados) {
			if(e.getNroLegajo().equals(nroLegajoBuscar))//si se encuntra un empleado con ese nrolegajo buscado
				{
					empleadoBuscado=e;
					break;
				}
		}
		return empleadoBuscado;
	}
	
	//agregar un empleado
	public void agregarEmpleado(Integer nroLegajo, String nombre, String apellido, Calendar antiguedadEmpleado) throws ErrorControlador{
		Empleado empleadoBuscar=buscarEmpleado(nroLegajo);
		if(empleadoBuscar==null)//si no se encontro un empleado con ese nrolegajo
		{
			Empleado empleadoAgregar = new Empleado(nroLegajo, nombre,apellido,antiguedadEmpleado);
			addEmpleado(empleadoAgregar);
		}else {
			throw new ErrorControlador("Ya existe un empleado con el numero de Legajo "+nroLegajo+" ("+empleadoBuscar.toString()+")");
		}
	}
	
	//agregar dias correspondientes
	//tomar licencia
	//los modificar
	
}
