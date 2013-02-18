package chasqui.model.collection.attibuteInstance;

import java.io.Serializable;

import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.digitalobjects.DigitalObject;

public class AttributeInstance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2802291375297143517L;
	protected Attribute hasType;
	protected String Path;
	protected DigitalObject doDigitalObject;	
	protected AttributeInstance fatherAtribute; 
	
	
	
	public AttributeInstance(Attribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute) {
		super();
		this.hasType = hasType;
		Path = path;
		this.doDigitalObject = doDigitalObject;
		this.fatherAtribute = fatherAtribute;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Attribute getHasType() {
		return hasType;
	}
	
	public void setHasType(Attribute hasType) {
		this.hasType = hasType;
	}
	
	public AttributeInstance getFatherAtribute() {
		return fatherAtribute;
	}
	
	public void setFatherAtribute(AttributeInstance fatherAtribute) {
		this.fatherAtribute = fatherAtribute;
	}
	
}
