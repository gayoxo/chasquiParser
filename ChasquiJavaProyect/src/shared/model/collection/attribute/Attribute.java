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
	
	public void setFather(Attribute father) {
		Father = father;
	}
	
	public void setBrowseable(boolean browseable) {
		Browseable = browseable;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setSons(ArrayList<Attribute> sons) {
		Sons = sons;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public void ordena() {
		int in;
		for (int i = 1 ; i < Sons.size() ; i++) {
				    Attribute comp = Sons.get(i);
				    comp.ordena();
				    in = i;
				 
				    while (in > 0 && Sons.get(in - 1).getName().compareTo(comp.getName()) > 0){
				    	Sons.set(in, Sons.get(in-1));
				    	in--;
				 }
				 
				    Sons.set(in, comp);
				  

		} 
		
	}
}
