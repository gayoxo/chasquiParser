package oda.parser;

import general.server.msqlconection.MySQLConnectionOdA;

import java.awt.AWTEventMulticaster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import shared.model.collection.Collection;
import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.digitalobjects.DigitalObject;


public class OdA {
	
	private Collection toOda;

	public OdA(Collection coleccion) {
		toOda=coleccion;
	}

	public void preocess() {
		
		PreProceso p=new PreProceso();
		ArrayList<DigitalObject> DO=p.preproccesModel(toOda);
		
	//	processOV(DO);
	}

	



	private void processOV(ArrayList<DigitalObject> dO) {
		for (DigitalObject digitalObject : dO) {
			saveOV(digitalObject);
		}
		
	}

	private void saveOV(DigitalObject value) {
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`virtual_object` (`id`, `ispublic`) VALUES ("+value.getIdentifier()+", 'S');");
		String descripcion=value.getDescription();
		descripcion=descripcion.replaceAll("'", "\\\\'");
		MySQLConnectionOdA.RunQuerryINSERT("INSERT INTO `oda2011`.`text_data` (`idov`, `idseccion`, `value`) VALUES ("+value.getIdentifier()+", 111, '"+descripcion+"');");
	}

}
