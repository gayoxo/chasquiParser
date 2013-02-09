package chasqui.client.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import chasqui.parser.coleccion.ChasquiImplementationExtendCollection;

public class Lector {

	private static final String INSERTFILENAME = "Introduce nombre y ruta del archivo .dat a cargar";

	private static ChasquiImplementationExtendCollection Chasqui;
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Inicio();
		

	}
	private static void Inicio() {
		try {
	           
			System.out.println(INSERTFILENAME);
			BufferedReader leerEntrada = new BufferedReader(new InputStreamReader(System.in));
			String entrada = leerEntrada.readLine();
            ObjectInputStream file = new ObjectInputStream(new FileInputStream(entrada));
       
            Chasqui= (ChasquiImplementationExtendCollection) file.readObject();
            file.close();
            System.out.println(Chasqui.toString());
        } catch (ClassNotFoundException ex) {
             System.out.println(ex);
        } catch (IOException ex) {
             System.out.println(ex);
       }
		
	}

}
