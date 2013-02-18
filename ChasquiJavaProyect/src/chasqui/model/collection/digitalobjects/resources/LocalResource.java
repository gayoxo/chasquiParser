package chasqui.model.collection.digitalobjects.resources;

import chasqui.model.collection.digitalobjects.DigitalObject;

public class LocalResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8099523548519069411L;
	protected String Name;
	
	public LocalResource(DigitalObject padre, String name, String displayName,
			String descripcion, boolean visible) {
		super(padre, displayName, descripcion, visible);
		this.Name=name;
	}

	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
}
