package chasqui.parser.coleccion.atributos;

import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendDigitalObject extends DigitalObject implements ChasquiParseElement{

	
	public ExtendDigitalObject(Integer identifier, String description) {
		super(identifier, description);
	}



	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Sons.size() && !(Sons.get(counter).getName().equals(atribute.getName())))
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
		"DigitalObject (Objeto Digital: " + identifier + ") \n" +
		prefix + "." + "(Descripcion: " + description + ") \n"+processSons(prefix+"...");
		
	}
	
//	public static String remove1(String input) {
//	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
//	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
//	    String output = input;
//	    for (int i=0; i<original.length(); i++) {
//
//	        output = output.replace(original.charAt(i), ascii.charAt(i));
//	    }
//	    return output;
//	}
}
