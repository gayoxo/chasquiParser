package chaqui.client.main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

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
	        	Calendar c1 = Calendar.getInstance();
	        	Calendar c2 = new GregorianCalendar();
	        	String dia = Integer.toString(c2.get(Calendar.DATE));
	        	String mes = Integer.toString(c2.get(Calendar.MONTH)+1);
	        	String annio = Integer.toString(c2.get(Calendar.YEAR));
	        	c2.setTimeZone(TimeZone.getDefault());
	        	String hora = Integer.toString(c2.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c2.get(Calendar.MINUTE));
	            fichero = new FileWriter(dia+"-"+mes+"-"+annio+".txt");
	            pw = new PrintWriter(fichero);
	            pw.println("Collection: " + MySQLConnection.getDBSelected() +" At " + dia+"-"+mes+"-"+annio +" " + hora );
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
