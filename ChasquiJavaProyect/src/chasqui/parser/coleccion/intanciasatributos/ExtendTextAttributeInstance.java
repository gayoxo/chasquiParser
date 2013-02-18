package chasqui.parser.coleccion.intanciasatributos;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attibuteInstance.TextAttributeInstance;
import chasqui.model.collection.attribute.TextAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public class ExtendTextAttributeInstance extends TextAttributeInstance implements ChasquiParseElement{



	/**
	 * 
	 */
	private static final long serialVersionUID = -6786082254018690171L;

	public ExtendTextAttributeInstance(TextAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			String valor) {
		super(hasType, path, doDigitalObject, fatherAtribute, valor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Atributo : " + Path );
		SB.append(" =" + valor);
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub
		
	}

}
