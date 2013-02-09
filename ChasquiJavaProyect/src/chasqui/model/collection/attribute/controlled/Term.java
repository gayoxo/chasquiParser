package chasqui.model.collection.attribute.controlled;

import java.io.Serializable;

public class Term implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 407850953777136626L;
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
