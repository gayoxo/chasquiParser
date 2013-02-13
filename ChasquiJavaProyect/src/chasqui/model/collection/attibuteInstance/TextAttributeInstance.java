package chasqui.model.collection.attibuteInstance;

import chasqui.model.collection.attribute.TextAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;

public class TextAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8110818142689186669L;
	protected String valor;

	public TextAttributeInstance(TextAttribute hasType, String path, String valor,DigitalObject doDigitalObject) {
		super(hasType, path, doDigitalObject);
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
}
