package main;

//import control.Controlador;

import control.Controlador;
import control.Persistencia;
import exceptions.ErrorControlador;
import exceptions.ErrorPersistencia;
import java.util.logging.Level;
import java.util.logging.Logger;

//import exceptions.ErrorControlador;
public class Main {

	public static void main(String[] args) throws ErrorPersistencia {
           
            try {
                Persistencia persistencia;
                persistencia = new Persistencia(); 
                 Controlador controladora = new Controlador(persistencia);
            } catch (ErrorPersistencia ex) {
                throw new ErrorPersistencia(ex.getMessage());
            }
		
		
	}

}
