package chaqui.client.main;

import chaqui.parser.collection.ChasquiImplementationExtendCollection;
import chaqui.server.msqlconection.MySQLConnection;

public class Start {
	
	
	
	
	private static final String WELLCOME_TEXT = "Bienveido al parseador de chasqui UCM";
	public static final String WARNING = "!!!WARNING : ";
	
	private static ChasquiImplementationExtendCollection Chasqui;
	
	
	public static void main(String[] args) {
		System.out.println(WELLCOME_TEXT);
		MySQLConnection.getInstance();
		Chasqui=new ChasquiImplementationExtendCollection();
		Chasqui.Process();
		System.out.println("Collection:");
		System.out.println(Chasqui.toString());
		
		
		
	}


	

}
