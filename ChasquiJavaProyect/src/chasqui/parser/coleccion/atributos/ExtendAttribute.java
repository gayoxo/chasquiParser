package chasqui.parser.coleccion.atributos;

import shared.model.collection.attribute.Attribute;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.coleccion.intanciasatributos.ExtendAttributeInstance;

public class ExtendAttribute extends Attribute implements ChasquiParseElement,AtributeElement{
	
	public ExtendAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
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
		name + "() \n"+processSons(prefix+"...");
		
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
	
	public String pathFather()
	{
		if (Father!=null)
		return ((AtributeElement)Father).pathFather()+"/" + name ;
		else return name;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new ExtendAttribute(name, Browseable, Father);
	}

	@Override
	public void Process() {

		
	}
}
