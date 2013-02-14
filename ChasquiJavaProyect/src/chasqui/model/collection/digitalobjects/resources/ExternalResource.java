package chasqui.model.collection.digitalobjects.resources;

import chasqui.model.collection.digitalobjects.DigitalObject;

public class ExternalResource extends Resource {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3246764936260149358L;
	protected Resource Target;

	public Resource getTarget() {
		return Target;
	}

	public void setTarget(Resource target) {
		Target = target;
	}

	public ExternalResource(DigitalObject padre, 
			String displayName, String descripcion, 
			boolean visible, Resource target) {
		super(padre,displayName, descripcion,visible);
		Target = target;
	}
	
	
}
