package chasqui.model.collection.attribute;

import chasqui.model.collection.attribute.controlled.Vocabulary;

public abstract class ControlledAttribute extends Attribute {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4073709784261611241L;
	protected Vocabulary vocabulary;
	
	public ControlledAttribute(String name, boolean browseable, Attribute father) {
		super(name, browseable, father);
		vocabulary=new Vocabulary();
	}

}
