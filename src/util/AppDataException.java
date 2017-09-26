package util;

public class AppDataException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public AppDataException(){
		super();
	}
	
	public AppDataException(String message){
		super(message);
	}
	
	public AppDataException(String message, Throwable cause){
		super(message,cause);		
	}

}