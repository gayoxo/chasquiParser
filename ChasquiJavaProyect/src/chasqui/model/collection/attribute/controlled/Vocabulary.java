package chasqui.model.collection.attribute.controlled;

import java.util.ArrayList;

public class Vocabulary {

	protected ArrayList<Term> list;
	
	public Vocabulary() {
		list=new ArrayList<>();
	}
	
	public ArrayList<Term> getList() {
		return list;
	}
	
	public Term addTerm(Term term)
	{

	int counter=0;
	while (counter<list.size() && !list.get(counter).getTerm().equals(term.getTerm()))
		counter++;
	if (counter==list.size())
		{
		list.add(term);
		return term;
		}
	else return list.get(counter);
	
	}
	
	
	
}
