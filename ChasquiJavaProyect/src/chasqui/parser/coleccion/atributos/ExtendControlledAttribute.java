package chasqui.parser.coleccion.atributos;

import shared.model.collection.attribute.Attribute;
import shared.model.collection.attribute.ControlledAttribute;
import shared.model.collection.attribute.controlled.Term;
import chasqui.parser.AtributeElement;
import chasqui.parser.ChasquiParseElement;

public abstract class ExtendControlledAttribute extends ControlledAttribute implements ChasquiParseElement,AtributeElement {

	public ExtendControlledAttribute(String name, boolean browseable,
			Attribute father) {
		super(name, browseable, father);
	}
	
	public Term addTerm(Term term)
	{

	int counter=0;
	while (counter<vocabulary.getList().size() && !(vocabulary.getList().get(counter).getTerm().equals(term.getTerm())))
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
			SB.append("{");
			for (Term term : vocabulary.getList()) {
				SB.append(((ExtendTerm)term).toString(string));
				SB.append(",");
			}
			if ( vocabulary.getList().size()>0) SB.deleteCharAt(SB.length()-1);
			SB.append("}");
			SB.append("\n");
		}
		return SB.toString();
	}

	@Override
	public String toString(String prefix) {
		return prefix + 
				name+"(C) \n" + processVocabulary(prefix+".") + processSons(prefix+"...") ;
	}
	
	protected abstract void process_Vocabulary();
	
	public Attribute addAtributos(Attribute atribute) {
		int counter=0;
		while (counter<Sons.size() && !(Sons.get(counter).getName().equals(atribute.getName())))
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
	
	public String pathFather()
	{
		if (Father!=null)
			return ((AtributeElement)Father).pathFather()+"/" + name ;
			else return name;
	}
	
	public Term findTerm(String Termino)
	{
		for (Term termino : vocabulary.getList()) {
			if (remove1(termino.getTerm().toLowerCase()).equals(remove1(Termino.toLowerCase())))
				return termino;
		} 
		
		return null;
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