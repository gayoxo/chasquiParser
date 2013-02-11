package chasqui.parser.coleccion.atributos;

import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.DateAttribute;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendDateAttribute extends DateAttribute  implements ChasquiParseElement{

	public ExtendDateAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Sons.size() && !Sons.get(counter).getName().equals(atribute.getName()))
			counter++;
		if (counter==Sons.size())
			{
			Sons.add(atribute);
			return atribute;
			}
		else return Sons.get(counter);
	}
	

	protected String processSons(String string) {
		StringBuffer SB=new StringBuffer();
		for (Attribute son : Sons) {
			SB.append(((ChasquiParseElement)son).toString(string));
		}
		return SB.toString();
	}
	
	@Override
	public String toString(String prefix) {
		return prefix + 
		"DateAttribute (Atributo: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}
}
