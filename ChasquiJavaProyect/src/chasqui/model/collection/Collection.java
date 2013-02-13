package chasqui.model.collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.model.collection.digitalobjects.resources.Resource;

public abstract class Collection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2024710713540254451L;
	protected ArrayList<Attribute> Atributos;
	protected HashMap<Integer,DigitalObject> ObjetosDigitales;
	protected ArrayList<Resource> RecursosGeneral;
	
	
	protected Collection() {
		Atributos=new ArrayList<Attribute>();
		ObjetosDigitales=new HashMap<Integer,DigitalObject>();
		RecursosGeneral=new ArrayList<Resource>();
	}

	
}
