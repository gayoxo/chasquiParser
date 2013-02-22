package chasqui.parser.coleccion.intanciasatributos;

import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.InstanceAttribute;

public class ExtendAttributeInstance extends AttributeInstance  implements ChasquiParseElement,InstanceAttribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5517631662884914122L;

	public ExtendAttributeInstance(Attribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute) {
		super(hasType, path, doDigitalObject, fatherAtribute);

	}

	@Override
	public String toString(String prefix) {

		return toStringinterno(prefix);
	}

	@Override
	public void Process() {
	
	}

	@Override
	public String toStringinterno(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Categoria : " + hasType.getName());
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
