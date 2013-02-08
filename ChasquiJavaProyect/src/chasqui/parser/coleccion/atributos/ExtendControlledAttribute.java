package chasqui.parser.coleccion.atributos;

import chasqui.model.collection.attribute.Attribute;
import chasqui.model.collection.attribute.ControlledAttribute;
import chasqui.model.collection.attribute.controlled.Term;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendControlledAttribute extends ControlledAttribute implements ChasquiParseElement {

	public ExtendControlledAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);
	}
	
	public Term addTerm(Term term)
	{

	int counter=0;
	while (counter<vocabulary.getList().size() && !(remove1(vocabulary.getList().get(counter).getTerm()).toLowerCase().equals(remove1(term.getTerm()).toLowerCase())))
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
		if (!vocabulary.getList().isEmpty()){
			SB.append(string);
			SB.append("Vocabulary: (");
			for (Term term : vocabulary.getList()) {
				SB.append(((ExtendTerm)term).toString(string));
				SB.append(",");
			}
			if ( vocabulary.getList().size()>0) SB.deleteCharAt(SB.length()-1);
			SB.append(")");
			SB.append("\n");
		}
		return SB.toString();
	}

	@Override
	public String toString(String prefix) {
		return prefix + 
				"ControlledAttribute(Atributo: "+name+")(Browseable: " + Browseable + ") \n" + processVocabulary(prefix+".") + processSons(prefix+"...") ;
	}
	
	protected abstract void process_Vocabulary();
	
	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Sons.size() && !(remove1(Sons.get(counter).getName().toLowerCase()).equals(remove1(atribute.getName()).toLowerCase())))
			counter++;
		if (counter==Sons.size())
			{
			Sons.add(atribute);
			return atribute;
			}
		else return Sons.get(counter);
	}
	
	protected String processSons(String string) {
		StringBuffer SB=new StringBuffer();
		for (Attribute son : Sons) {
			SB.append(((ChasquiParseElement)son).toString(string));
		}
		return SB.toString();
	}
	
	public static String remove1(String input) {
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {

	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }
	    return output;
	}
}