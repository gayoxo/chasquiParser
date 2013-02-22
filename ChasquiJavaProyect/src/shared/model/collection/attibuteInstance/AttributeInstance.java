package shared.model.collection.attibuteInstance;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.digitalobjects.DigitalObject;


public class AttributeInstance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2802291375297143517L;
	protected Attribute hasType;
	protected String Path;
	protected DigitalObject doDigitalObject;	
	protected AttributeInstance fatherAtribute; 
	protected ArrayList<AttributeInstance> sons;
	
	
	
	public AttributeInstance(Attribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute) {
		super();
		this.hasType = hasType;
		Path = path;
		this.doDigitalObject = doDigitalObject;
		this.fatherAtribute = fatherAtribute;
		sons=new ArrayList<AttributeInstance>();
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
	
	public DigitalObject getDoDigitalObject() {
		return doDigitalObject;
	}
	
	public void setDoDigitalObject(DigitalObject doDigitalObject) {
		this.doDigitalObject = doDigitalObject;
	}
	
	public ArrayList<AttributeInstance> getSons() {
		return sons;
	}
	
	public void setSons(ArrayList<AttributeInstance> sons) {
		this.sons = sons;
	}
	
	
}
