package control;

import java.util.ArrayList;
import modelo.Empleado;
import java.util.List;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import exceptions.ErrorPersistencia;

/**
 *
 * @author Damian Stetson
 */
public class Persistencia {

    private final String configuracion = "/hibernate1.cfg.xml";
	private SessionFactory factory;
	private Session sesion;

	public Persistencia() throws ErrorPersistencia {
		this.crearConexion();
	}

	/**
	 * Permite cerrar todas las conexiones activas.
	 * 
	 * @throws NotificarError
	 */
	protected void desconectar() throws ErrorPersistencia {
		/*
		 * Primero se cierra la sesi�n.
		 */
		this.cerrarSesion();

		/*
		 * Se cierra la conexi�n a la base de datos desde el sessionFactory.
		 */
		if (this.factory != null) {
			if (this.factory.isOpen()) {
				this.factory.close();
			}
		}
	}

	/**
	 * Permite cerrar la sesi�n actual de la base de datos.
	 * 
	 * @throws NotificarError
	 */
	protected void cerrarSesion() throws ErrorPersistencia {
		try {
			if (this.sesion != null) {
				if (this.sesion.isConnected()) {
					this.sesion.disconnect();
				}

				if (this.sesion.isOpen()) {
					this.sesion.close();
				}
			}
		} catch (HibernateException e) {
			throw new ErrorPersistencia(e.getMessage());
		}

	}

	/**
	 * Comprueba si la sesi�n est� abierta, caso contrario la abre.
	 * 
	 * @throws NotificarError en caso de que no se puede establecer una conexi�n
	 *                           con la base de datos.
	 */
	protected void comprobarSesion() throws ErrorPersistencia {
		String mensaje = null;

		try {
			/*
			 * Se verifica que el factory est� disponible.
			 */
			if (this.factory.isClosed()) {
				/*
				 * Se intenta abrir la conexi�n.
				 */
				this.crearConexion();
			}

			/*
			 * Se verifica si la sesi�n est� disponible.
			 */
			if (this.sesion == null || !this.sesion.isConnected()) {
				/*
				 * Se crea una nueva sesi�n.
				 */
				this.sesion = this.factory.openSession();
			}
		} catch (HibernateException e) {
			mensaje = "Se ha interrumpido la conexi�n con la base de datos.";
			throw new ErrorPersistencia(mensaje);
		}
	}

	private void crearConexion() throws ErrorPersistencia {
		try {
			this.factory = new Configuration().configure(this.configuracion).buildSessionFactory();
		} catch (HibernateException e) {
			throw new ErrorPersistencia(e.getMessage());
		}
	}
	
	/**
	 * Permite guardar o actualizar las modificaciones realizadas a una instancia.
	 * 
	 * @param instancia
	 * @throws NotificarError
	 */
	public void guardarOActualizarInstancia(Object instancia) throws ErrorPersistencia {

		/*
		 * Se comprueba la sesi�n.
		 */
		this.comprobarSesion();

		/*
		 * Se una transacci�n.
		 */
		Transaction tx = this.sesion.beginTransaction();

		try {
                    this.sesion.saveOrUpdate(instancia);
                    tx.commit();
		} catch (HibernateException e) {
                    tx.rollback();
                    throw new ErrorPersistencia(e.getMessage());
		}
	}
    
    
    void guardarOmodificar(Empleado empleadoAsignarLicencia) {

    }
    
//clase empleado
    public ArrayList<Empleado> buscarEmpleados() {
        ArrayList<Empleado> listemp=null;
        return listemp;
    }

    public void agregarEmpleados(ArrayList<Empleado> empleados) {

    }

    public void agregarEmpleado(Empleado empleAgregar) {

    }

    Empleado buscarEmpleado(Integer nroLegajoBuscar) {
        Empleado emp=null;
        return emp;
    }

    
    
}
