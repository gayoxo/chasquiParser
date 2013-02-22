package chasqui.parser.coleccion.atributos;

import shared.model.collection.attribute.controlled.Term;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;


public class ExtendTerm extends Term implements ChasquiParseElement,AtributeElement{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1900667780589163302L;

	public ExtendTerm(String name) {
		super(name);
	}

	@Override
	public String toString(String prefix) {
			return term;
	}

	@Override
	public void Process() {
				
	}

	public String pathFather()
	{
		return null;
	}
	
}
