package chasqui.parser.coleccion.intanciasatributos;

import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attibuteInstance.ControlledAttributeInstance;
import shared.model.collection.attribute.ControlledAttribute;
import shared.model.collection.attribute.controlled.Term;
import shared.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;
import chasqui.parser.InstanceAttribute;

public class ExtendControlledAttributeInstance extends
		ControlledAttributeInstance implements ChasquiParseElement,InstanceAttribute{

	/**
	 * 
	 */
	private static final long serialVersionUID = 389865004857001933L;

	
	public ExtendControlledAttributeInstance(ControlledAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			Term termino) {
		super(hasType, path, doDigitalObject, fatherAtribute, termino);
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
//		SB.append(prefix);
//		SB.append("Atributo : " + Path );
//		SB.append(" =");
//		if (termino!=null)
//			SB.append(termino.getTerm());
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
		SB.append(" =");
		if (termino!=null)
			SB.append(termino.getTerm());
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
