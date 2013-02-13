package chasqui.parser.coleccion.objetosdigitales;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendDigitalObject extends DigitalObject implements ChasquiParseElement{

	private static final String internalprefix="..";
	
	public ExtendDigitalObject(Integer identifier, String description) {
		super(identifier, description);
	}


	protected String processSons(String string) {
		StringBuffer SB=new StringBuffer();
		for (AttributeInstance son : Sons) {
			SB.append(((ChasquiParseElement)son).toString(string));
		}
		return SB.toString();
	}
	
	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("OV: " + identifier + "\n");
		SB.append(prefix+internalprefix);
		SB.append("Descripcion: " + description + " \n");
		SB.append(processSons(prefix+internalprefix));
		return SB.toString();
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
