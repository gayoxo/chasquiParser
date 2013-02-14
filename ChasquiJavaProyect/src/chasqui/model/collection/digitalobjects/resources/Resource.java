package chasqui.model.collection.digitalobjects.resources;

import java.io.Serializable;

import chasqui.model.collection.digitalobjects.DigitalObject;

public abstract class Resource implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8132459898169400259L;
	protected DigitalObject Padre;
	protected String DisplayName;
	protected String Descripcion;
	protected boolean visible;
	
	
	
	
	
	public Resource(DigitalObject padre,String displayName,
			String descripcion,boolean visible) {
		super();
		Padre = padre;
		DisplayName = displayName;
		Descripcion = descripcion;
		this.visible = visible;
	}
	
	
	public DigitalObject getPadre() {
		return Padre;
	}
	public void setPadre(DigitalObject padre) {
		Padre = padre;
	}
	public String getDisplayName() {
		return DisplayName;
	}
	public void setDisplayName(String displayName) {
		DisplayName = displayName;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	
	
}
