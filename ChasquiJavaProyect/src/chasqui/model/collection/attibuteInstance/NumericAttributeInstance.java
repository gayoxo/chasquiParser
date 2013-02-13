package chasqui.model.collection.attibuteInstance;

import chasqui.model.collection.attribute.NumericAttribute;

public abstract class NumericAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1123409776263158835L;
	protected float valor;
	
	
	public NumericAttributeInstance(NumericAttribute hasType, String path, float valor) {
		super(hasType, path);
		this.valor = valor;
	}


	public float getValor() {
		return valor;
	}


	public void setValor(float valor) {
		this.valor = valor;
	}

	
}
