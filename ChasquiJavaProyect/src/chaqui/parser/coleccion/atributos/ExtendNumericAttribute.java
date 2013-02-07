package chaqui.parser.coleccion.atributos;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.NumericAttribute;

public abstract class ExtendNumericAttribute extends NumericAttribute  implements ChasquiParseElement{

	public ExtendNumericAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Sons.size() && !Sons.get(counter).getName().equals(atribute))
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
		"NumericAttribute (Atributo: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}
}
