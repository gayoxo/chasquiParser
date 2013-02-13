package chasqui.model.collection.digitalobjects.resources;

import chasqui.model.collection.digitalobjects.DigitalObject;

public class LocalResource extends Resource {

	protected String archivo;
	
	public LocalResource(DigitalObject padre, String name, String displayName,
			String descripcion, String tipo, boolean visible) {
		super(padre, name, displayName, descripcion, tipo, visible);
		archivo=name;
	}

}
