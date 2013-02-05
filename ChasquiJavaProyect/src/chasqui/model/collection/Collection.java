package chasqui.model.collection;

import java.util.ArrayList;

import chasqui.model.collection.attribute.Attribute;

public abstract class Collection {

	private ArrayList<Attribute> Atributos;
	
	
	protected Collection() {
		Atributos=new ArrayList<Attribute>();
	}
	
	public ArrayList<Attribute> getAtributos() {
		return Atributos;
	}
	
	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Atributos.size() && !Atributos.get(counter).getName().equals(atribute))
			counter++;
		if (counter==Atributos.size())
			{
			Atributos.add(atribute);
			return atribute;
			}
		else return Atributos.get(counter);
	}
}
