package chaqui.parser.collection.attribute;

import chaqui.parser.ChasquiParseElement;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.model.collection.attribute.controlled.Vocabulary;


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
