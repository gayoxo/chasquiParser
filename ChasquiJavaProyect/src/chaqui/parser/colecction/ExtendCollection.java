package chaqui.parser.colecction;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.Collection;
import chasqui.model.collection.attribute.Attribute;

public abstract class ExtendCollection extends Collection implements ChasquiParseElement{

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		for (Attribute hijos : Atributos) {
			SB.append(((ChasquiParseElement)hijos).toString("..."));
		}
		return SB.toString();
	}
	
	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Atributos.size() && !Atributos.get(counter).getName().equals(atribute.getName()))
			counter++;
		if (counter==Atributos.size())
			{
			Atributos.add(atribute);
			return atribute;
			}
		else return Atributos.get(counter);
	}
	
}
