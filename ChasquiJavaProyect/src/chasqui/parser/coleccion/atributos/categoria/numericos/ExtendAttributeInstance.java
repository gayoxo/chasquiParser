package chasqui.parser.coleccion.atributos.categoria.numericos;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.parser.ChasquiParseElement;

public class ExtendAttributeInstance extends AttributeInstance  implements ChasquiParseElement{

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

		return "";
	}

	@Override
	public void Process() {
		// TODO Auto-generated method stub
		
	}

}
