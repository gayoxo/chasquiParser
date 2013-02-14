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

	public DOResource(DigitalObject padre,String displayName,
			String descripcion, boolean visible,
			DigitalObject referencia) {
		super(padre, displayName, descripcion, visible);
		this.referencia = referencia;
	}
	
	

}
