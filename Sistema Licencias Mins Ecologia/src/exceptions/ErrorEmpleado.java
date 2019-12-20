package exceptions;

public class ErrorEmpleado extends Exception{
	
	private static final long serialVersionUID = 5946291686691554946L;

	public ErrorEmpleado(String mensajeError){
		super(mensajeError);
	}
}
