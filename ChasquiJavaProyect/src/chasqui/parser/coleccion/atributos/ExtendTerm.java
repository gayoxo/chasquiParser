package chasqui.parser.coleccion.atributos;

import chasqui.model.collection.attribute.controlled.Term;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;


public class ExtendTerm extends Term implements ChasquiParseElement,AtributeElement{

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
