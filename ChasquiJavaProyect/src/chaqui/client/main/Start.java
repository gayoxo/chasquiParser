package chaqui.client.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import chaqui.parser.collection.ChasquiImplementationCollection;
import chaqui.server.msqlconection.MySQLConnection;

public class Start {
	
	
	
	
	private static final String WELLCOME_TEXT = "Bienveido al parseador de chasqui UCM";
	public static final String WARNING = "!!!WARNING : ";
	
	private static ChasquiImplementationCollection Chasqui;
	
	
	public static void main(String[] args) {
		System.out.println(WELLCOME_TEXT);
		MySQLConnection.getInstance();
		Chasqui=new ChasquiImplementationCollection();
		Chasqui.Process();
		System.out.println("Collection:");
		System.out.println(Chasqui.toString());
		
		
		
	}


	

}
