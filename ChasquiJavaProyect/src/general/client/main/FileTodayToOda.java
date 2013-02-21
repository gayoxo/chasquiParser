package general.client.main;

import general.server.msqlconection.MySQLConnectionOdA;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import oda.parser.OdA;


import shared.model.collection.Collection;

import chasqui.parser.coleccion.ChasquiImplementationExtendCollection;

public class FileTodayToOda {

	public static final String WELLCOME_TEXT = "Bienveido al parseador a OdA";
	public static final String INSERTFILENAME = "Introduce nombre y ruta del archivo .dat a cargar";
	public static final String DescargaEnOdAFinalizado = "Descarga en OdA finalizada";
	
	private static ChasquiImplementationExtendCollection Chasqui;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Inicio();
		

	}
	private static void Inicio() {
		try {
			System.out.println(WELLCOME_TEXT);  
//			System.out.println(INSERTFILENAME);
			Calendar c2 = new GregorianCalendar();
	    	String dia = Integer.toString(c2.get(Calendar.DATE));
	    	String mes = Integer.toString(c2.get(Calendar.MONTH)+1);
	    	String annio = Integer.toString(c2.get(Calendar.YEAR));
	    	c2.setTimeZone(TimeZone.getDefault());
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(dia+"-"+mes+"-"+annio+".dat"));
       
            Chasqui= (ChasquiImplementationExtendCollection) file.readObject();
            file.close();
            MySQLConnectionOdA.getInstance();
	        OdA oda=new OdA(Chasqui);
			oda.preocess();	
			 System.out.println(DescargaEnOdAFinalizado);
            
            
        } catch (ClassNotFoundException ex) {
             System.out.println(ex);
        } catch (IOException ex) {
             System.out.println(ex);
       }
		
	}
	
	public static void fromFile(String Path) {
		try {
	          
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(Path));
       
            Chasqui= (ChasquiImplementationExtendCollection) file.readObject();
            file.close();
            System.out.println(Chasqui.toString());
        } catch (ClassNotFoundException ex) {
             System.out.println(ex);
        } catch (IOException ex) {
             System.out.println(ex);
       }
		
	}
	
	public static ChasquiImplementationExtendCollection getChasqui() {
		return Chasqui;
	}
	public static void inicioSinArchivo(Collection coleccion) {
		MySQLConnectionOdA.getInstance();
	    OdA oda=new OdA(coleccion);
	    oda.preocess();	
		System.out.println(FileTodayToOda.DescargaEnOdAFinalizado);
		
	}

}
