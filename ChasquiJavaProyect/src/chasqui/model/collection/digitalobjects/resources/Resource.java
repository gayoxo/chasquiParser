package chasqui.model.collection.digitalobjects.resources;

import java.io.Serializable;

import chasqui.model.collection.digitalobjects.DigitalObject;

public abstract class Resource implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8132459898169400259L;
	protected DigitalObject Padre;
	protected String Name;
	protected String DisplayName;
	protected String Descripcion;
	protected String Tipo;
	protected boolean visible;
	
	
	
	
	
	public Resource(DigitalObject padre, String name, String displayName,
			String descripcion, String tipo, boolean visible) {
		super();
		Padre = padre;
		Name = name;
		DisplayName = displayName;
		Descripcion = descripcion;
		Tipo = tipo;
		this.visible = visible;
	}
	
	
	public DigitalObject getPadre() {
		return Padre;
	}
	public void setPadre(DigitalObject padre) {
		Padre = padre;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
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
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	
	
}
