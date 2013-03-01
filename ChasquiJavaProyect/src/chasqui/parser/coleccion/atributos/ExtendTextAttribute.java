package chasqui.parser.coleccion.atributos;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.attribute.TextAttribute;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;

public class ExtendTextAttribute extends TextAttribute implements ChasquiParseElement,AtributeElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274307539763274304L;



	public ExtendTextAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		// TODO Auto-generated constructor stub
	}



	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Sons.size() && !(remove1(Sons.get(counter).getName().toUpperCase()).equals(remove1(atribute.getName().toUpperCase()))))
			counter++;
		if (counter==Sons.size())
			{
			atribute.setName(remove1(atribute.getName().toUpperCase()));
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
				name + "(T)\n"+processSons(prefix+"...");
		
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
	
	public Object clone() throws CloneNotSupportedException {
		return new ExtendTextAttribute(name, Browseable, Father);
		
	}



	@Override
	public void Process() {
		// TODO Auto-generated method stub
		
	}
	
	public static String remove1(String input) {
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {

	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }
	    return output;
	}
}
