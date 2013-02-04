package chasqui.model.collection.attribute.controlled;

public class Term {

	protected String term;
	
	public Term(String name) {
		term=name;
	}
	
	public String getTerm() {
		return term;
	}
	
	public void setTerm(String term) {
		this.term = term;
	}
}
