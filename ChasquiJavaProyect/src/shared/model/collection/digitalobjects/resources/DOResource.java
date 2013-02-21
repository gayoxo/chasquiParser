package shared.model.collection.digitalobjects.resources;

import shared.model.collection.digitalobjects.DigitalObject;

public class DOResource extends Resource {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7330760902226864765L;
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
