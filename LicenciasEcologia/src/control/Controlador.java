package control;

import java.util.ArrayList;
import java.util.Calendar;

import exceptions.ErrorControlador;
import modelo.*;

public class Controlador {
    
        /**
         * constructor que exige incializacion a BD
         * @param persistencia 
         */
        Controlador(Persistencia persistencia){
            this.persistencia=persistencia;
        }
        
        private Persistencia persistencia;
	private ArrayList<DiaNoHabil> feriados= new ArrayList<DiaNoHabil>();
	private Calendar fechaActual;
	
	//getters
	public ArrayList<Empleado> getEmpleados() {
		return persistencia.buscarEmpleados();
	}
	public ArrayList<DiaNoHabil> getFeriados() {
		return feriados;
	}
	public Calendar getFechaActual() {
		return fechaActual;
	}
	
	//setters
	public void setEmpleados(ArrayList<Empleado> empleados) {
		this.persistencia.agregarEmpleados(empleados);
	}
	public void setFeriados(ArrayList<DiaNoHabil> feriados) {
		this.feriados = feriados;
	}
	public void setFechaActual(Calendar fechaActual) {
		this.fechaActual = fechaActual;
	}
	public void addEmpleado(Empleado empleAgregar) {
		this.persistencia.agregarEmpleado(empleAgregar);
	}
	public void addDiaNoHabil(DiaNoHabil diaNoHabilAgregar) {
		this.feriados.add(diaNoHabilAgregar);
	}
////////////////////////////// LICENCIA SEGUN CANTIDAD DIAS	///////////////
	/**
         * devuelve un objeto de tipo DiasTomados para utilizarlo en un arrayslist y mostralo como la funcion "generarLicenciaPorCantDias" pero en este caso seria de a uno,
         * de manera individual
         * @param nroLegajoAgente
         * @param cantDiasGenerarLicencia
         * @param anioDeDiasCorrespondientes
         * @return diasTomadosDeAnioRetornar
         * @throws ErrorControlador 
         */
	
	public DiasTomados tomarDiasDeUnAnioCorrespondiente(Integer nroLegajoAgente, Integer cantDiasGenerarLicencia,Calendar anioDeDiasCorrespondientes) throws ErrorControlador{
		//obj empleado a asignar
		Empleado empleadoGenerarLic = null;
		empleadoGenerarLic=buscarEmpleado(nroLegajoAgente);
		
		//correspondintes de ese anio buscado
		DiasCorrespondientesPorAnio correspBuscado=null;
		//obj a retornar
		DiasTomados diasTomadosDeAnioRetornar =null;
		
		if(empleadoGenerarLic!=null) {//si encuentra al empleado
			correspBuscado=empleadoGenerarLic.buscarDiasCorrespondientesPorAnio(anioDeDiasCorrespondientes);
			if(correspBuscado!=null) {//si encuentra los correspondintes de ese anio del agente
				if(correspBuscado.getDiasDisponibles()>=cantDiasGenerarLicencia)//si quedan dias disponibles y alcanzan para los q se pide
				{	
					//le tomamos los dias a los dias corresp
					correspBuscado.setDiasDisponibles(correspBuscado.getDiasDisponibles()-cantDiasGenerarLicencia);
					//le sumamos a los ocupados
					correspBuscado.setDiasOcupados(correspBuscado.getDiasOcupados()+cantDiasGenerarLicencia);
					//creamos el obj a retornar
					DiasTomados diasTomadosDeAnio =new DiasTomados(cantDiasGenerarLicencia,correspBuscado);
					diasTomadosDeAnioRetornar=diasTomadosDeAnio;//para poder retonar o null, o la instancia creada
				}else {
					Calendar anioLic=correspBuscado.getAnio();
					throw new ErrorControlador("El Empleado("+empleadoGenerarLic.toString()+") no cuenta con los dias suficientes para generar la Licencia(Pedidos: "+cantDiasGenerarLicencia+" Disponibles: "+correspBuscado.getDiasDisponibles()+" del a�o: "+anioLic.get(Calendar.YEAR)+").");	
				}
			}else {
				throw new ErrorControlador("No se encontraron los dias correspondintes del anio("+anioDeDiasCorrespondientes.get(Calendar.DAY_OF_YEAR)+").");
			}
		}else {
			throw new ErrorControlador("No se encontro el Empleado con el numero de Legajo("+nroLegajoAgente+").");
		}
		return diasTomadosDeAnioRetornar;
	}
        
        /**
         * este metodo muestra la cantidad de dias q se tomaran del anio correspondiente
         * (si es q tiene dias sino salta exception) para mostrarse en una vista
         * @param nroLegajoAgente
         * @param cantDiasGenerarLicencia
         * @param fechaInicio
         * @return losDiasTomados
         * @throws ErrorControlador 
         */
	public ArrayList<DiasTomados> generarLicenciaPorCantDias(Integer nroLegajoAgente, int cantDiasGenerarLicencia,Calendar fechaInicio) throws ErrorControlador {
		//contadores
		int cantDiasTomadosDeAnio=0,diasOcupados=0;
		// empleado a asignar
		Empleado empleadoGenerarLic = null;
		empleadoGenerarLic=buscarEmpleado(nroLegajoAgente);
		//coleccion de dias que se tomara por anio a mostrar en la ventana
		ArrayList<DiasTomados> losDiasTomados=new ArrayList<DiasTomados>();
		//calendar que se fija que no sea ni sabado, ni dormingo, ni feriado(se recorre el array dias no habiles)
		Calendar fechaContar = fechaInicio;
		
		if(empleadoGenerarLic==null) {
			throw new ErrorControlador("No se encontro el Empleado con el numero de Legajo("+nroLegajoAgente+").");
		}else {
			for(DiasCorrespondientesPorAnio d :empleadoGenerarLic.getCorrespondiestes()) {
				if(cantDiasGenerarLicencia==0)//si ya tomamos la cantidad de dias deseados salimos del bucle que "toma" los dias
				{
					break;
				}else {
					//guardo los dias ocupados para no tocar el del objeto
					diasOcupados=d.getDiasOcupados();
					//si hay dias para tomar de ese anio y aun faltan tomar dias
					while((d.getDiasDisponibles()-diasOcupados > 0)&&(cantDiasGenerarLicencia>0))
					{
								if(isFeriado(fechaContar)==false)//si es un dia habil
								{
									diasOcupados++;//ocupamos un dia	
									cantDiasGenerarLicencia--;//sacamos uno de la cantidad q debemos tomar
									cantDiasTomadosDeAnio++;
								}
								if(!(d.getDiasDisponibles( )-diasOcupados > 0))//si ya no le quedan dias a esa licencia
								{
									DiasTomados diasTomadosLic = new DiasTomados(cantDiasTomadosDeAnio,d);//tomamos los datos sacados de esa lic
									losDiasTomados.add(diasTomadosLic);
									cantDiasTomadosDeAnio=0;
									break;
								}
							fechaContar.add(Calendar.DAY_OF_MONTH, 1);//aumentamos un dia
					}
				}
			}//fin del for de dias correspondientes por anio
			if(cantDiasGenerarLicencia>0)//quiere decir q no se pudo tomar todos los dias que se pidio
			{
				losDiasTomados.clear();//como no se pudo tomar la licencia... borramos la lista que estabamos haciendo de los dias a tomar
				throw new ErrorControlador("El Empleado("+empleadoGenerarLic.toString()+") no cuenta con los dias suficientes para generar la Licencia(Le faltaron: "+cantDiasGenerarLicencia+" dias para poder generar la Licencia).");	
			}
		}
		return losDiasTomados;
	}

////////////////////////////////FUNCION PARA CONOCER SI ES FERIADO///////////////////////////////
        /**
         * funcion que retorna falso si esa fecha no es sabado/domingo/diaNoHabil
         * @param fecha
         * @return result
         */
private boolean isFeriado(Calendar fecha) {
	boolean result=false;
        //si es sabado o domingo
	if((fecha.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)||(fecha.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY))
	{
		result=true;
	}else {
		//ahora nos fijamos  si es un dia no habil...
		for(DiaNoHabil d : feriados)
		{										
			if(d.getFeriado().compareTo(fecha)==0)//si son el mismo dia.... es feriado
			{
				result=true;
			}
		}
	}
	return result;
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
                empleadoBuscado=persistencia.buscarEmpleados(nroLegajoBuscar);
		return empleadoBuscado;
	}
	
	//agregar un empleado
	public void agregarEmpleado(Integer nroLegajo, String nombre, String apellido, Calendar antiguedadEmpleado) throws ErrorControlador{
		Empleado empleadoBuscar=buscarEmpleado(nroLegajo);
		if(empleadoBuscar==null)//si no se encontro un empleado con ese nrolegajo
		{
			Empleado empleadoAgregar = new Empleado(nroLegajo, nombre,apellido,antiguedadEmpleado);
			persistencia.agregarEmpleado(empleadoAgregar);
		}else {
			throw new ErrorControlador("Ya existe un empleado con el numero de Legajo "+nroLegajo+" ("+empleadoBuscar.toString()+")");
		}
	}
	//los modificar
	
}
