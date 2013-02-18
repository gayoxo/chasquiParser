package chasqui.model.collection.attibuteInstance;

import chasqui.model.collection.attribute.NumericAttribute;
import chasqui.model.collection.digitalobjects.DigitalObject;

public abstract class NumericAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1123409776263158835L;
	protected float valor;
	
	
	

	public NumericAttributeInstance(NumericAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			float valor) {
		super(hasType, path, doDigitalObject, fatherAtribute);
		this.valor = valor;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}

	
}
