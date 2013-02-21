package shared.model.collection.attibuteInstance;


import shared.model.collection.attribute.ControlledAttribute;
import shared.model.collection.attribute.controlled.Term;
import shared.model.collection.digitalobjects.DigitalObject;

public class ControlledAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197527830113363682L;
	protected Term termino;
	

	

	public ControlledAttributeInstance(ControlledAttribute hasType, String path,
			DigitalObject doDigitalObject, AttributeInstance fatherAtribute,
			Term termino) {
		super(hasType, path, doDigitalObject, fatherAtribute);
		this.termino = termino;
	}

	public Term getTermino() {
		return termino;
	}
	
	public void setTermino(Term termino) {
		this.termino = termino;
	}
	

}
