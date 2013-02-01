package chasqui.model.collection.attribute;

import java.util.ArrayList;

public abstract class Attribute {

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
	
	
}
