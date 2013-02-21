package chasqui.parser.coleccion.intanciasatributos;

import shared.model.collection.attibuteInstance.AttributeInstance;
import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;

public class ExtendNoShowAttributeInstance extends ExtendAttributeInstance {

	public ExtendNoShowAttributeInstance(Attribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute) {
		super(hasType, path, doDigitalObject, fatherAtribute);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString(String prefix) {
		return "";
	}
}
