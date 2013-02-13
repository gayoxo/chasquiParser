package chasqui.model.collection.digitalobjects.resources;

import chasqui.model.collection.digitalobjects.DigitalObject;

public class DOResource extends Resource {

	
	protected DigitalObject referencia;

	public DigitalObject getReferencia() {
		return referencia;
	}

	public void setReferencia(DigitalObject referencia) {
		this.referencia = referencia;
	}

	public DOResource(DigitalObject padre, String name, String displayName,
			String descripcion, String tipo, boolean visible,
			DigitalObject referencia) {
		super(padre, name, displayName, descripcion, tipo, visible);
		this.referencia = referencia;
	}
	
	

}
