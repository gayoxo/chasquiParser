package chasqui.model.collection;

import java.io.Serializable;
import java.util.ArrayList;

import chasqui.model.collection.attribute.Attribute;

public abstract class Collection implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2024710713540254451L;
	protected ArrayList<Attribute> Atributos;
	
	
	protected Collection() {
		Atributos=new ArrayList<Attribute>();
	}

	
}
