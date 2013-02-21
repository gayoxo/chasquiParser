package chasqui.parser.coleccion.intanciasatributos;

import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attibuteInstance.TextAttributeInstance;
import shared.model.collection.attribute.TextAttribute;
import shared.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.InstanceAttribute;

public class ExtendTextAttributeInstance extends TextAttributeInstance implements ChasquiParseElement,InstanceAttribute{



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
//		SB.append(prefix);
//		SB.append("Atributo : " + Path );
//		SB.append(" =" + valor);
//		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toStringinterno(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Atributo : " + hasType.getName() );
		SB.append(" =" + valor);
		SB.append("\n");
		SB.append(processSons(prefix+".."));
		return SB.toString();
	}

	private Object processSons(String prefixheredado) {
		StringBuffer SB=new StringBuffer();
		for (AttributeInstance element : sons) {
			SB.append(((InstanceAttribute)element).toStringinterno(prefixheredado));
		} 
		return SB.toString();
	}
}
