package chaqui.parser.collection.attribute;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.TextAttribute;

public class Tatributos_metadatos_Cdescripcion_TextAttribute extends TextAttribute implements ChasquiParseElement{


	public Tatributos_metadatos_Cdescripcion_TextAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);

		
	}


	@Override
	public String toString(String prefix) {
		return prefix + 
		TextAttribute.class.toString()
		+ "(Name: " + name + ")(Browseable: " + Browseable + ") \n"+processSons(prefix+"...");
		
	}

	private String processSons(String string) {
		StringBuffer SB=new StringBuffer();
		for (Attribute son : Sons) {
			SB.append(((ChasquiParseElement)son).toString(string));
		}
		return SB.toString();
	}


	@Override
	public void Process() {
	
		
	}
}
