package chasqui.model.collection.digitalobjects;

import java.io.Serializable;
import java.util.ArrayList;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.attribute.Attribute;

public class DigitalObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 551698657515311395L;
	
	protected Integer identifier;
	protected String description;
	protected ArrayList<AttributeInstance> Sons;
	
	
	
	public DigitalObject(Integer identifier, String description) {
		super();
		this.identifier = identifier;
		this.description = description;
		Sons=new ArrayList<AttributeInstance>(); 
	}
	
	public Integer getIdentifier() {
		return identifier;
	}
	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<AttributeInstance> getSons() {
		return Sons;
	}
	
	public void setSons(ArrayList<AttributeInstance> sons) {
		Sons = sons;
	}
	

}
