package chasqui.model.collection.attribute.controlled;

import chasqui.model.collection.attribute.Attribute;

public abstract class ControlledAttribute extends Attribute {
	
	public ControlledAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
	}

}
