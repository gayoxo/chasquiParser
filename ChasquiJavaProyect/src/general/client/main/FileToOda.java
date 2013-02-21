package general.client.main;

import general.server.msqlconection.MySQLConnectionOdA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import oda.parser.OdA;


import shared.model.collection.Collection;

import chasqui.parser.coleccion.ChasquiImplementationExtendCollection;

public class FileToOda {

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
			System.out.println(INSERTFILENAME);
			BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
			String entrada = leerEntrada.readLine();
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(entrada));
       
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
		System.out.println(FileToOda.DescargaEnOdAFinalizado);
		
	}

}
