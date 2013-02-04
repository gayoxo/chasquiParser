package chaqui.parser.collection.attribute.controlled;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.model.collection.attribute.controlled.Vocabulary;


public class ImplementacionTerm extends Term implements ChasquiParseElement{

	public ImplementacionTerm(String name) {
		super(name);
	}

	@Override
	public String toString(String prefix) {
		return prefix + 
				Vocabulary.class.toString() +
				"(Term: " + term + ")\n";
	}

	@Override
	public void Process() {
				
	}

}
