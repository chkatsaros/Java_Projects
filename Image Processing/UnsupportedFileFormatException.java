package ce325.hw2;
import java.lang.*;

public class UnsupportedFileFormatException extends Exception {
	
	String myMsg;
	
	public UnsupportedFileFormatException () {}
	
	public UnsupportedFileFormatException (String msg) {
		
		myMsg = msg;	
	}
}

