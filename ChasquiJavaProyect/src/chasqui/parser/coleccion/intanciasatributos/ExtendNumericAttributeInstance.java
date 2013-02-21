package chasqui.parser.coleccion.intanciasatributos;

import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attibuteInstance.NumericAttributeInstance;
import shared.model.collection.attribute.NumericAttribute;
import shared.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.InstanceAttribute;

public class ExtendNumericAttributeInstance extends NumericAttributeInstance implements ChasquiParseElement,InstanceAttribute {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2076405891213304888L;

	public ExtendNumericAttributeInstance(NumericAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			float valor) {
		super(hasType, path, doDigitalObject, fatherAtribute, valor);
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
