package oda.parser;

import general.server.msqlconection.MySQLConnectionOdA;

import java.util.HashMap;
import java.util.Map.Entry;

import shared.model.collection.Collection;
import shared.model.collection.digitalobjects.DigitalObject;


public class OdA {
	
	private Collection toOda;

	public OdA(Collection coleccion) {
		toOda=coleccion;
	}

	public void preocess() {
		
		processOV(toOda.getObjetosDigitales());
	}

	private void processOV(HashMap<Integer, DigitalObject> hashMap) {
		for (Entry<Integer, DigitalObject> digitalObject : hashMap.entrySet()) {
			saveOV(digitalObject.getValue());
		}
		
	}

	private void saveOV(DigitalObject value) {
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`virtual_object` (`id`, `ispublic`) VALUES ("+value.getIdentifier()+", 'S');");
		String descripcion=value.getDescription();
		descripcion=descripcion.replaceAll("'", "\\\\'");
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`text_data` (`idov`, `idseccion`, `value`) VALUES ("+value.getIdentifier()+", 111, '"+descripcion+"');");
	}

}
