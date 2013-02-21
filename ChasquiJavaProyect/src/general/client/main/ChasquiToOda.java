package general.client.main;

import general.server.msqlconection.MySQLConnectionChasqui;
import general.server.msqlconection.MySQLConnectionOdA;
import oda.parser.OdA;
import shared.model.collection.Collection;
import chasqui.parser.coleccion.ChasquiImplementationExtendCollection;

public class ChasquiToOda {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FileToOda.inicioSinArchivo(ChasquiToFIle.InicioSinArchivo());
		 
	}

}
