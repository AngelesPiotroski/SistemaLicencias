
package exceptions;

public class ErrorPersistencia extends Exception{
    private static final long serialVersionUID = 1L;

	public ErrorPersistencia(String mensajeError){
		super(mensajeError);
	}
}
