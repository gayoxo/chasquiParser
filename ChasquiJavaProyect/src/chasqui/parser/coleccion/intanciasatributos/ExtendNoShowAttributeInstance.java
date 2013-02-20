package chasqui.parser.coleccion.intanciasatributos;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.digitalobjects.DigitalObject;

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
