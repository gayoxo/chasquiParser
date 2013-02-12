package chasqui.model.collection.digitalobjects;

import java.io.Serializable;
import java.util.ArrayList;

import chasqui.model.collection.attribute.Attribute;

public class DigitalObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 551698657515311395L;
	
	protected Integer identifier;
	protected String description;
	protected ArrayList<Attribute> Sons;
	
	
	
	public DigitalObject(Integer identifier, String description) {
		super();
		this.identifier = identifier;
		this.description = description;
		Sons=new ArrayList<Attribute>(); 
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
	
	public ArrayList<Attribute> getSons() {
		return Sons;
	}
	
	public void setSons(ArrayList<Attribute> sons) {
		Sons = sons;
	}
	

}
