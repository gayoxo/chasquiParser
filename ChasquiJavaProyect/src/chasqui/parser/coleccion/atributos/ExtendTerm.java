package chasqui.parser.coleccion.atributos;

import chasqui.model.collection.attribute.controlled.Term;
import chasqui.parser.ChasquiParseElement;


public class ExtendTerm extends Term implements ChasquiParseElement{

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

}
