package chasqui.model.collection;

import java.util.ArrayList;

import chasqui.model.collection.attribute.Attribute;

public abstract class Collection {

	protected ArrayList<Attribute> Atributos;
	
	
	protected Collection() {
		Atributos=new ArrayList<Attribute>();
	}

	
}
