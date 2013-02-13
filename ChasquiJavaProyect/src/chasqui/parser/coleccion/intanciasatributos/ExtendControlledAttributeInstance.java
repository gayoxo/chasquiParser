package chasqui.parser.coleccion.intanciasatributos;

import chasqui.model.collection.attibuteInstance.ControlledAttributeInstance;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public class ExtendControlledAttributeInstance extends
		ControlledAttributeInstance implements ChasquiParseElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = 389865004857001933L;

	public ExtendControlledAttributeInstance(ControlledAttribute hasType, String path,
			Term termino,DigitalObject doDigitalObject) {
		super(hasType, path, termino, doDigitalObject);
	}

	@Override
	public String toString(String prefix) {
		StringBuffer SB=new StringBuffer();
		SB.append(prefix);
		SB.append("Atributo : " + Path );
		SB.append(" =" + termino.getTerm());
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub
		
	}

}
