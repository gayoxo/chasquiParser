package chaqui.client.main;

import java.io.FileWriter;
import java.io.PrintWriter;

import chaqui.parser.colecction.ChasquiImplementationExtendCollection;
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
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {
	            fichero = new FileWriter("prueba.txt");
	            pw = new PrintWriter(fichero);
	                pw.println(Chasqui.toString());

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	           try {
	           if (null != fichero)
	              fichero.close();
	           } catch (Exception e2) {
	              e2.printStackTrace();
	           }
	        }
		
		
	}


	

}
