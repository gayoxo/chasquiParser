package chaqui.parser.collection.attribute;

import chaqui.parser.ChasquiParseElement;
import chaqui.parser.collection.attribute.controlled.ImplementacionTerm;
import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;

public abstract class ExtendControlledAttribute extends ControlledAttribute implements ChasquiParseElement {

	public ExtendControlledAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);
	}
	
	public Term addTerm(Term term)
	{

	int counter=0;
	while (counter<vocabulary.getList().size() && !vocabulary.getList().get(counter).getTerm().equals(term.getTerm()))
		counter++;
	if (counter==vocabulary.getList().size())
		{
		vocabulary.getList().add(term);
		return term;
		}
	else return vocabulary.getList().get(counter);
	
	}
	
	

	protected String processVocabulary(String string) {
		StringBuffer SB=new StringBuffer();
		SB.append(string);
		SB.append("Vocabulary: (");
		for (Term term : vocabulary.getList()) {
			SB.append(((ImplementacionTerm)term).toString(string));
			SB.append(",");
		}
		if ( vocabulary.getList().size()>0) SB.deleteCharAt(SB.length()-1);
		SB.append(")");
		SB.append("\n");
		return SB.toString();
	}

	@Override
	public String toString(String prefix) {
		return prefix + 
				"ControlledAttribute(Atributo: "+name+")(Browseable: " + Browseable + ") \n" + processVocabulary(prefix+"...");
	}
	
}
