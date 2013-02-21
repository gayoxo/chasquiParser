package shared.model.collection.attribute;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8247000451879876288L;
	protected String name;
	protected boolean Browseable;
	protected Attribute Father;
	protected ArrayList<Attribute> Sons;
	
	public Attribute(String name, boolean browseable, Attribute father) {
		super();
		this.name = name;
		Browseable = browseable;
		Father = father;
		Sons=new ArrayList<Attribute>();
	}

	public String getName() {
		return name;
	}

	public boolean isBrowseable() {
		return Browseable;
	}

	public Attribute getFather() {
		return Father;
	}

	public ArrayList<Attribute> getSons() {
		return Sons;
	}
	
	
}
