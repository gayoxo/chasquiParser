package chasqui.parser.coleccion.atributos;

import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.NumericAttribute;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendNumericAttribute extends NumericAttribute  implements ChasquiParseElement,AtributeElement{

	public ExtendNumericAttribute(String name, boolean browseable, Attribute father) {
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
		name + "(N) \n"+processSons(prefix+"...");
		
	}
	
	public String pathFather()
	{
		if (Father!=null)
			return ((AtributeElement)Father).pathFather()+"/" + name ;
			else return name;
	}
	
	
}
