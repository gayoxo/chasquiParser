package chasqui.model.collection.attibuteInstance;

import java.io.Serializable;

import chasqui.model.collection.attribute.Attribute;

public class AttributeInstance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2802291375297143517L;
	protected Attribute hasType;
	protected String Path;
	
	
	public AttributeInstance(Attribute hasType, String path) {
		super();
		this.hasType = hasType;
		Path = path;
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
}
