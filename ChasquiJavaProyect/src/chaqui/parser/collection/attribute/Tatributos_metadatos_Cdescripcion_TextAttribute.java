package chaqui.parser.collection.attribute;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.TextAttribute;

public class Tatributos_metadatos_Cdescripcion_TextAttribute extends ExtendTextAttribute implements ChasquiParseElement{


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

	

	@Override
	public void Process() {
	
		
	}
}
