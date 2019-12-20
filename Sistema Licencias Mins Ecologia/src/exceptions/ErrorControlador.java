package exceptions;

public class ErrorControlador extends Exception{

	private static final long serialVersionUID = 1L;

	public ErrorControlador(String mensajeError){
		super(mensajeError);
	}
}
