package chasqui.model.collection.digitalobjects;

import java.io.Serializable;
import java.util.ArrayList;

import chasqui.model.collection.attibuteInstance.AttributeInstance;
import chasqui.model.collection.digitalobjects.resources.Resource;

public class DigitalObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 551698657515311395L;
	
	protected Integer identifier;
	protected String description;
	protected ArrayList<AttributeInstance> Atributos;
	protected ArrayList<Resource> Recursos;
	
	
	
	public DigitalObject(Integer identifier, String description) {
		super();
		this.identifier = identifier;
		this.description = description;
		Atributos=new ArrayList<AttributeInstance>(); 
		Recursos=new ArrayList<Resource>();
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
		return Atributos;
	}
	
	public void setSons(ArrayList<AttributeInstance> sons) {
		Atributos = sons;
	}
	
	public ArrayList<Resource> getRecursos() {
		return Recursos;
	}
	
	public void setRecursos(ArrayList<Resource> recursos) {
		Recursos = recursos;
	}
	
}
