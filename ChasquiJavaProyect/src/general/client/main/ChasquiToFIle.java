package general.client.main;

import general.server.msqlconection.MySQLConnectionChasqui;
import general.server.msqlconection.MySQLConnectionOdA;
import general.server.msqlconection.MySQLConnectionChasqui.DB;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import oda.parser.OdA;


import shared.model.collection.Collection;

import chasqui.parser.coleccion.ChasquiImplementationExtendCollection;

public class ChasquiToFIle {
	
	
	
	
	public static final String WELLCOME_TEXT = "Bienveido al parseador de chasqui UCM";
	public static final String WARNING = "!!!WARNING : ";
	public static final String CapturaChasquiFinalizado = "Captura chasqui finalizada";
	
	private static ChasquiImplementationExtendCollection Chasqui;
	
	
	public static void main(String[] args) {
		Inicio();
		
	}

	public static Collection InicioSinArchivo() 
	{
		System.out.println(ChasquiToFIle.WELLCOME_TEXT);
		MySQLConnectionChasqui.getInstance();
		Chasqui = new ChasquiImplementationExtendCollection();
		Chasqui.Process();
		System.out.println(ChasquiToFIle.CapturaChasquiFinalizado);
		return Chasqui;
	}

	private static void Inicio() {
		System.out.println(WELLCOME_TEXT);
		MySQLConnectionChasqui.getInstance();
		Chasqui=new ChasquiImplementationExtendCollection();
		Chasqui.Process();
		//System.out.println(Chasqui.toString());
		
		Calendar c2 = new GregorianCalendar();
    	String dia = Integer.toString(c2.get(Calendar.DATE));
    	String mes = Integer.toString(c2.get(Calendar.MONTH)+1);
    	String annio = Integer.toString(c2.get(Calendar.YEAR));
    	c2.setTimeZone(TimeZone.getDefault());
    	String hora = Integer.toString(c2.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c2.get(Calendar.MINUTE));
		 FileWriter fichero = null;
	        PrintWriter pw = null;
	        try
	        {

	        	
	            fichero = new FileWriter(dia+"-"+mes+"-"+annio+".txt");
	            pw = new PrintWriter(fichero);
	            pw.println("Collection: " + MySQLConnectionChasqui.getDBSelected() +" At " + dia+"-"+mes+"-"+annio +" " + hora );
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
		
	        try
			{
			
				// Se abre el fichero donde se hará la copia
				FileOutputStream fileOutput = new FileOutputStream (dia+"-"+mes+"-"+annio+".dat");
				ObjectOutputStream objectOuput = new ObjectOutputStream(fileOutput);
				
				objectOuput.writeObject(Chasqui);


				// Cierre de los ficheros
				objectOuput.close();
				fileOutput.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	        
	        
	        System.out.println(CapturaChasquiFinalizado);
	      
		
	}

	
	public static String Ecritor(DB db) {
		StringBuffer SB=new StringBuffer();
		SB.append(WELLCOME_TEXT + "\n");
		MySQLConnectionChasqui.getInstance(db);
		Chasqui=new ChasquiImplementationExtendCollection();
		Chasqui.Process();
		SB.append(Chasqui.toString());
		return SB.toString();
		
	}

	public static String Ecritor(String dbNameIP,int port,String User,String Password) {
		StringBuffer SB=new StringBuffer();
		SB.append(WELLCOME_TEXT + "\n");
		MySQLConnectionChasqui.getInstance(dbNameIP,port,User,Password);
		Chasqui=new ChasquiImplementationExtendCollection();
		Chasqui.Process();
		SB.append(Chasqui.toString());
		return SB.toString();
		
	}
	
	public static ChasquiImplementationExtendCollection getChasqui() {
		return Chasqui;
	}
	
	public static void toFileTXT(String Path) {
		Calendar c2 = new GregorianCalendar();
    	String dia = Integer.toString(c2.get(Calendar.DATE));
    	String mes = Integer.toString(c2.get(Calendar.MONTH)+1);
    	String annio = Integer.toString(c2.get(Calendar.YEAR));
    	c2.setTimeZone(TimeZone.getDefault());
    	String hora = Integer.toString(c2.get(Calendar.HOUR_OF_DAY))+":"+Integer.toString(c2.get(Calendar.MINUTE));
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {

        	
            fichero = new FileWriter(Path);
            pw = new PrintWriter(fichero);
            pw.println("Collection: " + MySQLConnectionChasqui.getDBSelected() +" At " + dia+"-"+mes+"-"+annio +" " + hora );
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
	
	public static void ToFileDat(String Path) {
		
		 try
			{
			
				// Se abre el fichero donde se hará la copia
				FileOutputStream fileOutput = new FileOutputStream (Path);
				ObjectOutputStream objectOuput = new ObjectOutputStream(fileOutput);
				
				objectOuput.writeObject(Chasqui);


				// Cierre de los ficheros
				objectOuput.close();
				fileOutput.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}

}
