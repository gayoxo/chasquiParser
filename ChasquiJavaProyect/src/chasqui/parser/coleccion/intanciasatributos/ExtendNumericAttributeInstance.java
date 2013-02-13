package chasqui.parser.coleccion.intanciasatributos;

import chasqui.model.collection.attibuteInstance.NumericAttributeInstance;
import chasqui.model.collection.attribute.NumericAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public class ExtendNumericAttributeInstance extends NumericAttributeInstance implements ChasquiParseElement {

	public ExtendNumericAttributeInstance(NumericAttribute hasType, String path,
			float valor,DigitalObject doDigitalObject) {
		super(hasType, path, valor, doDigitalObject);
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
