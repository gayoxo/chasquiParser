package shared.model.collection.attibuteInstance;

import shared.model.collection.attribute.TextAttribute;
import shared.model.collection.digitalobjects.DigitalObject;

public class TextAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8110818142689186669L;
	protected String valor;

	

	public TextAttributeInstance(TextAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			String valor) {
		super(hasType, path, doDigitalObject, fatherAtribute);
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
}
