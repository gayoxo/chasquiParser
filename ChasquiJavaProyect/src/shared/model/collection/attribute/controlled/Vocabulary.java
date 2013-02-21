package shared.model.collection.attribute.controlled;

import java.io.Serializable;
import java.util.ArrayList;

public class Vocabulary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3691846174757556535L;
	protected ArrayList<Term> list;
	
	public Vocabulary() {
		list=new ArrayList<>();
	}
	
	public ArrayList<Term> getList() {
		return list;
	}
	
	
	
	
	
}
