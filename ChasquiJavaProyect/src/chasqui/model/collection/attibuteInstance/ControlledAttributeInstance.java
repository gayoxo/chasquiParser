package chasqui.model.collection.attibuteInstance;


import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public class ControlledAttributeInstance extends AttributeInstance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5197527830113363682L;
	protected Term termino;
	public ControlledAttributeInstance(ControlledAttribute hasType, String path,
			Term termino) {
		super(hasType, path);
		this.termino = termino;
	}

	public Term getTermino() {
		return termino;
	}
	
	public void setTermino(Term termino) {
		this.termino = termino;
	}
	

}