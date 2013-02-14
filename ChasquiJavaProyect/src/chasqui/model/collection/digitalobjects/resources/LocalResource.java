package chasqui.model.collection.digitalobjects.resources;

import chasqui.model.collection.digitalobjects.DigitalObject;

public class LocalResource extends Resource {

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
