package chasqui.parser.coleccion.objetosdigitales.recursos;

import chasqui.model.collection.digitalobjects.DigitalObject;
import chasqui.model.collection.digitalobjects.resources.Resource;

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

	public ExternalResource(DigitalObject padre, String name,
			String displayName, String descripcion, String tipo,
			boolean visible, Resource target) {
		super(padre, name, displayName, descripcion, tipo, visible);
		Target = target;
	}
	
	
}
